package cloudNative.limjickchan.service

import cloudNative.limjickchan.dto.EmailMessageDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Service
@Transactional
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
) {

    @Value("\${MAIL_USERNAME}")
    lateinit var myEmail: String
    fun sendEmailWithTemplate(emailMessageDto: EmailMessageDto) {
        val context = Context()
        context.setVariable("name", emailMessageDto.name)
        context.setVariable("emailAddress", emailMessageDto.emailAddress)
        context.setVariable("companyUrl", emailMessageDto.companyUrl)
        context.setVariable("address", emailMessageDto.address)
        context.setVariable("job", emailMessageDto.job)
        context.setVariable("comment", emailMessageDto.comment)

        val preparatory = MimeMessagePreparator { mimeMessage ->
            val helper = MimeMessageHelper(mimeMessage, "UTF-8")

            val content = templateEngine.process("email", context)

            helper.setTo(myEmail)
            helper.setFrom(myEmail)
            helper.setSubject("test")

            helper.setText(content, true)
        }

        javaMailSender.send(preparatory)
    }
}
