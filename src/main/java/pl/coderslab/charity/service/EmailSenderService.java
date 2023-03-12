package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public MimeMessage prepareMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("lukasz.zdankowski.appbot@gmail.com");
        File file = new File("src/main/resources/Files/signature.svg");
        mimeMessageHelper.addAttachment("With love...", file);

        return mimeMessage;
    }

    public void sendSummary(Principal principal, Donation donation) throws MessagingException {
        MimeMessage mimeMessage = prepareMail();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - informacje o odbiorze");
        mimeMessageHelper.setTo(principal.getName());

        User user = userRepository.findByEmail(principal.getName()).orElse(null);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Witaj " + user.getName() + "\n")
                .append("Dziękujemy za przekazanie darów" + "\n")
                .append("Kurier pojawi się u Pani/Pana w dniu: " + donation.getPickUpDate() + "\n")
                .append("o godzinie: " + donation.getPickUpTime() + "\n");
        mimeMessageHelper.setText(stringBuilder.toString());
        javaMailSender.send(mimeMessage);
    }

    public void sendRegistration(String email) throws MessagingException {
        MimeMessage mimeMessage = prepareMail();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - potwierdzenie rejestracji");
        mimeMessageHelper.setTo(email);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Cieszymy się, że jesteś zainteresowany pomaganiem innym." + "\n")
                .append("Kliknij poniższy link aby potwierdzić rejestrację:" + "\n")
                .append("http://localhost:8080/registry-confirmation?" + BCrypt.hashpw(email, BCrypt.gensalt()));
        mimeMessageHelper.setText(stringBuilder.toString());
        javaMailSender.send(mimeMessage);
    }

    public void sendEmail(String emailAddress, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = prepareMail();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(emailAddress);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        javaMailSender.send(mimeMessage);
    }
}
