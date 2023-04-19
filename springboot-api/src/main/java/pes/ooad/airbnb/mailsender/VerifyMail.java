package pes.ooad.airbnb.mailsender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.util.OTPGenerator;

@Service
public class VerifyMail {

    private final JavaMailSender mailSender;

    @Autowired
    public VerifyMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toEmail, String toFirstName, String toLastName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("AirBnB - verification");
        Integer g = OTPGenerator.generateOTP();
        CurrentUser.otp = g;
        message.setText("Hello " + toFirstName+ " " + toLastName + ", \n Your OTP is " + g);
        System.out.println("Hello " + toFirstName+ " " + toLastName + ", \n Your OTP is " + g);
        mailSender.send(message);
    }
}
