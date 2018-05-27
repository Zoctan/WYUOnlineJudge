package com.zoctan.api.util;

import com.zoctan.api.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮箱工具
 *
 * @author Zoctan
 * @date 2018/5/27
 */
@Component
public class EmailUtil {
    private final static Logger log = LoggerFactory.getLogger(EmailUtil.class);
    @Value("${spring.mail.to}")
    private String to;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.subject}")
    private String subject;
    @Resource
    private JavaMailSender sender;

    /**
     * 发送纯文本的简单邮件
     */
    public boolean sendSimpleMail(final Feedback feedback) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.username);
        message.setTo(this.to);
        message.setSubject(this.subject + feedback.getEmail());
        message.setText(feedback.getContent());
        try {
            this.sender.send(message);
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 发送html格式的邮件
     */
    public boolean sendHtmlMail(final Feedback feedback) {
        final MimeMessage message = this.sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.username);
            helper.setTo(this.to);
            helper.setSubject(this.subject + feedback.getEmail());
            helper.setText(feedback.getContent(), true);

            this.sender.send(message);
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
