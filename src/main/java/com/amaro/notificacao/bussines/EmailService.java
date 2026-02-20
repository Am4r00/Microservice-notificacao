package com.amaro.notificacao.bussines;

import com.amaro.notificacao.bussines.dto.TarefasDTO;
import com.amaro.notificacao.infrastructure.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${envio.email.remetente}")
    public static String remetente;

    @Value("${envio.email.NomeRemetente}")
    public static String nomeRemetente;

    public void enviarEmail(TarefasDTO dto){
        try{
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem,true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(remetente,nomeRemetente));
            mimeMessageHelper.setTo(InternetAddress.parse(dto.getEmailUsuario()));
            mimeMessageHelper.setSubject("Notificação de tarefa");

            Context context = new Context();
            context.setVariable("nomeTarefa",dto.getNome());
            context.setVariable("dataEvento",dto.getDataEvento());
            context.setVariable("descricao",dto.getDescricao());

            String template = templateEngine.process("notificacao", context);
            mimeMessageHelper.setText(template,true);
            javaMailSender.send(mensagem);
        }catch(MessagingException | UnsupportedEncodingException e){
            throw new EmailException("Não foi possivel enviar o email " + e.getCause());
        }
    }
}
