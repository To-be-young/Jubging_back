package com.example.jubging.Service;

import com.example.jubging.DTO.EmailValidateDTO;
import com.example.jubging.Model.EmailValidationCode;
import com.example.jubging.Repository.EmailValidateCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender emailSender;
    private final EmailValidateCodeRepository emailValidateCodeRepository;
    private final EmailValidateDTO emailValidateDTO;
    public final String validationCode = createKey();

    @Transactional
    private MimeMessage createMessage(String to)throws Exception{
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + validationCode);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); //보내는 대상
        message.setSubject("인증코드: " + validationCode); //제목

        String msg="";
//        msg += "<img width=\"120\" height=\"36\" style=\"margin-top: 0; margin-right: 0; margin-bottom: 32px; margin-left: 0px; padding-right: 30px; padding-left: 30px;\" src=\"https://slack.com/x-a1607371436052/img/slack_logo_240.png\" alt=\"\" loading=\"lazy\">";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 Jubgging 가입 창에 있는 코드 입력란에 적어주세요</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += validationCode;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("Jubgging@naver.com","Jubgging")); //보내는 사람

        emailValidateCodeRepository.save(emailValidateDTO.toEntity(to, validationCode));

        return message;
    }

    // 인증코드 만들기
    @Transactional
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }

        return key.toString();
    }

    @Transactional
    public void sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public boolean verifyEmailCode(String email, String code){
        EmailValidationCode emailValidationCode = emailValidateCodeRepository.findByEmail(email).get();
        String verifyCode = emailValidationCode.getCode();

        log.info("DB속 인증코드: " + verifyCode);
        log.info("입력 코드: " + code);
        if (verifyCode.equals(code)){
            return true;
        }
        else {
            return false;
        }
    }
}