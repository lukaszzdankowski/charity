package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Token;
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
    private final TokenService tokenService;

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

    public void sendRegistration(User user) throws MessagingException {
        MimeMessage mimeMessage = prepareMail();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - potwierdzenie rejestracji");
        mimeMessageHelper.setTo(user.getEmail());

        Token token = tokenService.generateToken(user, "registration");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Cieszymy się, że jesteś zainteresowany pomaganiem innym." + "\n")
                .append("Kliknij poniższy link aby potwierdzić rejestrację:" + "\n")
                .append("http://localhost:8080/token/"
                        + token.getHashCode());
        mimeMessageHelper.setText(stringBuilder.toString());

        javaMailSender.send(mimeMessage);
    }

    public void sendReminder(User user) throws MessagingException {
        MimeMessage mimeMessage = prepareMail();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("Oddam w dobre ręce - resetowanie hasła");
        mimeMessageHelper.setTo(user.getEmail());

        Token token = tokenService.generateToken(user, "passwordReset");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Kliknij poniższy link aby zresetować hasło:" + "\n")
                .append("http://localhost:8080/token/"
                        + token.getHashCode());
        mimeMessageHelper.setText(stringBuilder.toString());

        javaMailSender.send(mimeMessage);
    }
}
