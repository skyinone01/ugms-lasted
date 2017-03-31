package com.ug369.backend.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailUtils {

    private static String host = "mtp.163.com"; // smtp服务器
    private static String from = "skyinone@163.com"; // 发件人地址
    private static String user = "skyinone@163.com"; // 用户名
    private static String pwd = "Nokia8866!@#"; // 密码

    public static void sendEmail(String to, String subject, String content) {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
//        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("发送邮件成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}