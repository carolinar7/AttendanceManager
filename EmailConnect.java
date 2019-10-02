package SeniorProj;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailConnect {
    //insert the email you want to use
    private String userName = "";
    //insert the password for that email
    private String passWord = "suncoast_Chargers";
    private String recipient;
    private String subject;
    private String body;
    private String emailType;
    private String code;
   

    //makes a string from random characters of a larger string to be used in verifying email
    String getCodeGenerator() 
    { 
        String codeGenerator = "0123456789"+ "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(7); 
        for (int i = 0; i < 7; i++) {
            int index = (int)(codeGenerator.length()* Math.random()); 
            sb.append(codeGenerator.charAt(index)); 
        } 
        return sb.toString(); 
    } 
  
    public void setBody(String body) {
    	this.body = body;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    //email type that will be sent
    public void setEmailType(String username,String password, String code,String emailType) {
    if(emailType.equals("setUpPassword")) {
        	String code1 = code;
        	subject = "Confirm Account";
        	body = "Code: " + code1 + "";
    }
    if(emailType.equals("forgotPassword")) {
    	String password1 = password;
    	String userName = username;
    	subject = "Reset Password";
    	body = "Username: "+ userName + "\nPassword: " + password1 + "";
    }
    if(emailType.equals("contact")) {
    	subject = "Contact";
    	recipient = "suncoastAttendance@gmail.com";
    }
    if(emailType.equals("contact2")) {
    	subject = "Contact";
    	recipient = "suncoastAttendance@gmail.com";
    }
    if(emailType.equals("attendance")) {
    	subject = "Attendance";
    }
    }
    public void sendMail() throws MessagingException {
        sendFromGMail(userName, passWord, new String[]{recipient}, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) throws MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            throw new AddressException();
        } catch (MessagingException me) {
            throw new MessagingException();
        }
    }
}
