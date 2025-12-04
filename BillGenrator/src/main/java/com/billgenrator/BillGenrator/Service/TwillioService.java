package com.billgenrator.BillGenrator.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class TwillioService {

    @Value("${twilio.account_sid}")
    private String account_sid;

    @Value("${twilio.auth_token}")
    private String auth_token;

    @Value("${twilio.phone_Number}")//whatsapp number
    private String phone_Number;

    @Value("${twilio.number}")
    private String number;

    @Autowired
    private JavaMailSender mailSender;

    @PostConstruct
    public void initTwilio(){
        Twilio.init(account_sid, auth_token);
    }

    public void updateMsg(String toPhoneNumber, String msg){
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + toPhoneNumber),
                new com.twilio.type.PhoneNumber("whatsapp:" + phone_Number),
                msg
        ).create();
    }

    public void updateMsgText(String toPhoneNumber, String msg){
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toPhoneNumber),
                new com.twilio.type.PhoneNumber(number),
                msg
        ).create();
    }

    //mail send
    public void sendCSV(String toEmail,String subject,String text,String csvContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);

        ByteArrayResource csvAttachment = new ByteArrayResource(csvContent.getBytes());
        helper.addAttachment("today_report.csv", csvAttachment);

        mailSender.send(message);
    }
}
