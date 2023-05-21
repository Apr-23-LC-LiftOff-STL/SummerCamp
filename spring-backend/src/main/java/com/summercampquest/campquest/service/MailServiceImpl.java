package com.summercampquest.campquest.service;

import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MailServiceImpl {

//    @Autowired
//    private SendGrid sendGrid;
//
//    public MailServiceImpl() {
//    }
//
//    public void sendEmail(EmailRequest emailrequest) {
//
//        Mail mail = new Mail(new Email("saran.launchcode@gmail.com"), emailrequest.getSubject(), new Email(emailrequest.getTo()), new Content("text/plain", emailrequest.getBody()));
//        mail.setReplyTo(new Email());
//        Request request = new Request();
//
//        try {
//
//            request.setMethod(Method.POST);
//
//            request.addHeader("Authorization", "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");
//            request.addHeader("Access-Control-Allow-Origin", "*");
//            request.addHeader("Access-Control-Allow-Headers", "*");
//
//            request.setEndpoint("mail/send");
//
//            request.setBody(mail.build());
//
//            //SendGrid sendGrid1 = new SendGrid("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");
//            sendGrid.api(request);
//
//        } catch (IOException ex) {
//
//            System.out.println(ex.getMessage());
//
//        }
//
//    }

    @Autowired
    SendGrid sendGrid;
    public String sendEmail(String email, String token)  {

        try {
            Mail mail = prepareMail(email, token);

            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            if(response!=null) {


                System.out.println("response code from sendgrid"+response.getStatusCode() + "," + response.getBody());

            }

        } catch (IOException e) {


            e.printStackTrace();
            return "error in sent mail!";
        }

        return "mail has been sent check your inbox!";

    }

    public Mail prepareMail(String email, String token) {

        Mail mail = new Mail();

        Content content = new Content();
        content.setType("text/html");
        content.setValue("http://localhost:4200/reset-password?token="+token);

        mail.addContent(content);

        Email fromEmail = new Email();
        fromEmail.setEmail("saran.launchcode@gmail.com");
        mail.setFrom(fromEmail);

        Email to = new Email();
        to.setEmail(email);


        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.setSubject("launch code");
        mail.addPersonalization(personalization);

        return mail;
    }

}
