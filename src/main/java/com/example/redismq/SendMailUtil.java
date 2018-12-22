package com.example.redismq;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class SendMailUtil {
    private static final String USERNAME = "用户名"; // 发送人的用户名
    private static final String PASSWORD = "密码"; // 发送邮件人的密码
    private static final String HOSTNAME = "smtp.126.com"; // smtp服务器地址
    private static final String FROMADDRESS = USERNAME + "@126.com"; // 发件人邮箱
    private static final String FROMUSERNAME = "CNCD-码上中国博客"; // 发件人姓名，可随意写
    private static final String EMAILTITLE = "码上中国博客"; // 邮件标题
    private static final String EMAILCONTENT = "欢迎使用 CNCD-码上中国博客激活邮件 ，请点击右侧链接完成账号激活:\n";// 邮件内容

    public static void main(String[] args) {
        try {
            sendEmail("294805447@qq.com", "用户名：guopengfei,密码：guopengfei");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     * @param sendAddress 要发送的邮箱地址
     * @param content 发送的内容
     * @throws Exception
     */
    public static void sendEmail(String sendAddress, String content)
            throws Exception {

        // 创建一个连接属性。
        Properties props = new Properties(); //
        props.put("mail.smtp.host ", HOSTNAME); // 设置smtp的服务器地址是smtp.126.com
        props.put("mail.smtp.auth", "true"); // 设置smtp服务器要身份验证。
        props.put("mail.transport.protocol", "stmp");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        InternetAddress from = new InternetAddress(FROMADDRESS); // 发送人地址
        from.setPersonal(MimeUtility.encodeText(FROMUSERNAME)); // 发件人名
        message.setFrom(from);
        // InternetAddress to = new InternetAddress("taozhida11@sina.cn");
        // //收件人地址

        // 创建邮件体:
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(sendAddress));// 抄送给多个人的邮箱
        // message.setRecipient(Message.RecipientType.TO, to); // 只有抄送给自己的邮箱
        message.setSubject(MimeUtility.encodeText(EMAILTITLE)); // 邮件标题
        message.setSentDate(new Date());
        MimeMultipart msgMultipart = new MimeMultipart("mixed");// 指定为混合关系
        message.setContent(msgMultipart);
        // 邮件内容
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(
                "<body><div style='width: 1000px;height: 300px;margin: 0px auto;margin-bottom:20px;border:1px solid #92B0DD;background-color: #FFFFFf;'><h3>这是系统自动发送的邮件，请勿回复!</h3><br/>"+
                        content+"</div></body>",
                "text/html;charset=UTF-8");
        // TODO 组装的顺序非常重要，一定要先组装文本域，再组装文件
        msgMultipart.addBodyPart(htmlPart);
        // 组装附件
        // MimeBodyPart file = new MimeBodyPart();
        // FileDataSource file_datasource = new FileDataSource(
        // "D:\\report_data2.txt");
        // DataHandler dh = new DataHandler(file_datasource);
        // file.setDataHandler(dh);
        // // 附件区别内嵌内容的一个特点是有文件名，为防止中文乱码要编码
        // file.setFileName(MimeUtility.encodeText(dh.getName()));
        // msgMultipart.addBodyPart(file);
        message.saveChanges();

        // 发送邮件的过程:95188
        Transport transport = session.getTransport("smtp"); // 创建连接
        transport.connect(HOSTNAME, 25, USERNAME, PASSWORD); // 连接服务器
        // 服务名，端口，发送邮件用户名
        // （不要@后面），密码
        transport.sendMessage(message, message.getAllRecipients()); // 发送信息
        transport.close(); // 关闭
        System.out.println("发送完毕");

    }
}
