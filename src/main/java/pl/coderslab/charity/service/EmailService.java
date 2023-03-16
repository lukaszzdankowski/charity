package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;

import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TokenService tokenService;

    public void sendSummary(User user, Donation donation) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        FileDataSource fileDataSource = new FileDataSource(new File("src/main/webapp/resources/images/about-us.jpg"));
        mimeMessageHelper.addAttachment("With love...", fileDataSource);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - informacje o odbiorze");
        mimeMessageHelper.setTo(user.getEmail());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Witaj ").append(user.getName()).append("\n")
                .append("Dziękujemy za przekazanie darów\n")
                .append("Kurier pojawi się u Pani/Pana w dniu: ").append(donation.getPickUpDate()).append("\n")
                .append("o godzinie: ").append(donation.getPickUpTime());
        mimeMessageHelper.setText(stringBuilder.toString());

        javaMailSender.send(mimeMessage);
    }

    public void sendRegistration(User user) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        FileDataSource fileDataSource = new FileDataSource(new File("src/main/webapp/resources/images/about-us.jpg"));
        mimeMessageHelper.addAttachment("With love...", fileDataSource);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - potwierdzenie rejestracji");
        mimeMessageHelper.setTo(user.getEmail());

        Token token = tokenService.generateToken(user);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Cieszymy się, że jesteś zainteresowany pomaganiem innym.\n")
                .append("Kliknij poniższy link aby potwierdzić rejestrację:\n")
                .append("http://localhost:8080/guest/register-token/").append(token.getHashCode());
        mimeMessageHelper.setText(stringBuilder.toString());

        javaMailSender.send(mimeMessage);
    }

    public void sendReminder(User user) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        FileDataSource fileDataSource = new FileDataSource(new File("src/main/webapp/resources/images/about-us.jpg"));
        mimeMessageHelper.addAttachment("With love...", fileDataSource);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - resetowanie hasła");
        mimeMessageHelper.setTo(user.getEmail());

        Token token = tokenService.generateToken(user);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Kliknij poniższy link aby zresetować hasło:\n")
                .append("http://localhost:8080/guest/password-token/").append(token.getHashCode());
        mimeMessageHelper.setText(stringBuilder.toString());

        javaMailSender.send(mimeMessage);
    }
}
