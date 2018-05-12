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

@Component
public class EmailUtil {
    private final Logger log = LoggerFactory.getLogger(EmailUtil.class);
    @Value("${spring.mail.to}")
    private String TO;
    @Value("${spring.mail.username}")
    private String USERNAME;
    @Value("${spring.mail.subject}")
    private String SUBJECT;
    @Resource
    private JavaMailSender sender;

    /**
     * 发送纯文本的简单邮件
     */
    public boolean sendSimpleMail(final Feedback feedback) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.USERNAME);
        message.setTo(this.TO);
        message.setSubject(this.SUBJECT + feedback.getEmail());
        message.setText(feedback.getContent());
        try {
            this.sender.send(message);
            return true;
        } catch (final Exception e) {
            this.log.error(e.getMessage());
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
            helper.setFrom(this.USERNAME);
            helper.setTo(this.TO);
            helper.setSubject(this.SUBJECT + feedback.getEmail());
            helper.setText(feedback.getContent(), true);

            this.sender.send(message);
            return true;
        } catch (final Exception e) {
            this.log.error(e.getMessage());
            return false;
        }
    }
}
