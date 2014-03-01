package com.loanly.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.security.Security;
import java.util.Date;  
import java.util.Properties;  
  




import javax.mail.Authenticator;  
import javax.mail.Message.RecipientType;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

import com.loanly.model.Login;
import com.opensymphony.xwork2.ActionSupport;

  
public class EmailUtils {  
      
    /**
	 * 
	 */
    /** 
     * 注册成功后,向用户发送帐户激活链接的邮件 
     * @param user 未激活的用户 
     * @throws Exception 
     */  
    public static void sendAccountActivateEmail(Login user,String serverURL,String emailfrom,String emailpass,String emailserver) throws Exception {  
        Session session = getSession(emailfrom,emailpass,emailserver);  
        MimeMessage message = new MimeMessage(session);  
        try {  
            message.setSubject("帐户激活邮件");  
            message.setSentDate(new Date());  
            message.setFrom(new InternetAddress(emailfrom));  
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));  
            message.setContent("<a href='" + GenerateLinkUtils.generateActivateLink(user,serverURL)+"'>点击激活帐户</a>","text/html;charset=utf-8");  
            // 发送邮件  
            Transport.send(message);  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw e;
        }  
    }  
      
    /** 
     * 发送重设密码链接的邮件 
     * @throws Exception 
     */  
    public static void sendResetPasswordEmail(Login user,String serverURL,String emailfrom,String emailpass,String emailserver) throws Exception {  
        Session session = getSession(emailfrom,emailpass,emailserver);  
        MimeMessage message = new MimeMessage(session);  
        try {
            message.setSubject("找回您的帐户与密码");  
            message.setSentDate(new Date());  
            message.setFrom(new InternetAddress(emailfrom));  
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));  
            String resetUrl = GenerateLinkUtils.generateResetPwdLink(user,serverURL);
            message.setContent("请使用以下链接启用密码:<br/><a href='" + resetUrl +"'>点击重新设置密码</a>  <br/>如果邮件屏蔽超链接请将如下链接复制到浏览器的地址栏进行重置密码：<br/>"+ resetUrl,"text/html;charset=utf-8");  
            // 发送邮件  
            Transport.send(message);  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw e;
        }  
    }  
      
    public static Session getSession(final String emailfrom,final String emailpass,String emailserver) {  
        Properties props = new Properties();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.transport.protocol", "smtp");  
        //props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");  
        props.setProperty("mail.smtp.host", emailserver);
        props.setProperty("mail.smtp.port", "465");  
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                //String password = "moneyball001";
                /*
                InputStream is = EmailUtils.class.getResourceAsStream("password.dat");  
                byte[] b = new byte[1024];  
                try {
                    int len = is.read(b);  
                    password = new String(b,0,len);  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }
                */
                return new PasswordAuthentication(emailfrom, emailpass);  
            }  
              
        });  
        return session;  
    }  
}  