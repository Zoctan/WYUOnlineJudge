package com.zoctan.api.util;

import com.zoctan.api.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮箱工具
 *
 * @author Zoctan
 * @date 2018/5/27
 */
public class EmailUtil {
    private final static Logger log = LoggerFactory.getLogger(EmailUtil.class);
    /**
     * 邮件发送协议
     */
    @Value("${mail.protocol}")
    private static String protocol = "smtp";

    /**
     * SMTP邮件服务器
     */
    @Value("${mail.host}")
    private static String host;

    /**
     * SMTP邮件服务器默认端口
     */
    @Value("${mail.port}")
    private static String port;

    /**
     * 身份认证
     */
    @Value("${mail.auth}")
    private static String auth;

    /**
     * 用调试
     */
    @Value("${mail.debug}")
    private static String debug;

    /**
     * 邮件主题
     */
    @Value("${mail.subject}")
    private static String subject;
    /**
     * 发件人邮箱
     */
    @Value("${mail.username}")
    private static String username;
    /**
     * 密码
     */
    @Value("${mail.password}")
    private static String password;

    /**
     * 收件人
     */
    @Value("${mail.to}")
    private static String to;

    /**
     * 初始化连接邮件服务器的会话信息
     */
    private static Properties props;

    static {
        props = new Properties();
        props.setProperty("mail.enable", "true");
        props.setProperty("mail.transport.protocol", protocol);
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.auth", auth);
        props.setProperty("mail.debug", debug);
    }

    /**
     * 发送简单的文本邮件
     */
    public static boolean sendTextEmail(String to, String message) {
        try {
            // 创建Session实例对象
            Session session1 = Session.getDefaultInstance(props);

            // 创建MimeMessage实例对象
            MimeMessage mimeMessage = new MimeMessage(session1);
            // 设置发件人
            mimeMessage.setFrom(new InternetAddress(username));
            // 设置邮件主题
            mimeMessage.setSubject(subject);
            // 设置收件人
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            // 设置发送时间
            mimeMessage.setSentDate(new Date());
            // 设置纯文本内容为邮件正文
            mimeMessage.setText(message);
            // 保存并生成最终的邮件内容
            mimeMessage.saveChanges();

            // 获得Transport实例对象
            Transport transport = session1.getTransport();
            // 打开连接
            transport.connect("meijiajiang2016", "");
            // 将message对象传递给transport对象，将邮件发送出去
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            // 关闭连接
            transport.close();

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 发送简单的html邮件
     */
    public static boolean sendDefaultHtmlEmail(Feedback feedback) {
        return sendHtmlEmail(to, String.format("邮箱：%s<br/>内容：%s", feedback.getEmail(), feedback.getContent()));
    }


    /**
     * 发送简单的html邮件
     */
    public static boolean sendHtmlEmail(String to, String htmlMessage) {
        // 创建Session实例对象
        Session session1 = Session.getInstance(props, new MyAuthenticator());


        try {
            // 创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session1);
            // 设置邮件主题
            message.setSubject(subject);
            // 设置发送人
            message.setFrom(new InternetAddress(username));
            // 设置发送时间
            message.setSentDate(new Date());
            // 设置收件人
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
            // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
            message.setContent(htmlMessage, "text/html;charset=utf-8");

            //设置自定义发件人昵称
            String nick = "";
            nick = javax.mail.internet.MimeUtility.encodeText("WYU-OJ");
            message.setFrom(new InternetAddress(nick + " <" + username + ">"));
            // 保存并生成最终的邮件内容
            message.saveChanges();

            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 向邮件服务器提交认证信息
     */
    static class MyAuthenticator extends Authenticator {

        private String username;

        private String password;

        public MyAuthenticator() {
            super();
            this.password = EmailUtil.password;
            this.username = EmailUtil.username;
        }

        public MyAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}