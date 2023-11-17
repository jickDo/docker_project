package cloudNative.limjickchan.service

import cloudNative.limjickchan.access.client.MailAccessClient
import cloudNative.limjickchan.configuration.ContextGeneratorToMe
import cloudNative.limjickchan.constants.SecretNaverConst
import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.repository.EmailRepository
import jakarta.mail.MessagingException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.spring6.SpringTemplateEngine
import kotlin.random.Random

@Service
@Transactional
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val emailRepository: EmailRepository,
    private val mailAccessClient: MailAccessClient,
) {

    @Value("\${MAIL_USERNAME}")
    lateinit var myEmail: String
    fun sendEmailToMe(emailMessageDto: EmailMessageDto) {
        val context = ContextGeneratorToMe.configureContext(emailMessageDto)
        val preparatory = MimeMessagePreparator { mimeMessage ->
            val helper = MimeMessageHelper(mimeMessage, "UTF-8")

            val content = templateEngine.process("email", context)

            helper.setTo(myEmail)
            helper.setFrom(myEmail)
            helper.setSubject("test")

            helper.setText(content, true)
        }
        try {
            javaMailSender.send(preparatory)
            saveMail(emailMessageDto)
        } catch (e: MailException) {
            // MailException 처리
            println("메일 전송 실패: ${e.message}")
            // 적절한 로그 기록, 사용자 알림, 또는 다른 오류 처리
        } catch (e: MessagingException) {
            // MessagingException 처리
            println("메시징 오류: ${e.message}")
            // 적절한 로그 기록, 사용자 알림, 또는 다른 오류 처리
        } catch (e: Exception) {
            // 기타 모든 예외 처리
            println("알 수 없는 오류 발생: ${e.message}")
            // 적절한 로그 기록, 사용자 알림, 또는 다른 오류 처리
        }
    }

    fun saveMail(emailMessageDto: EmailMessageDto) {
        emailRepository.save(EmailMessageDto.toEmail(emailMessageDto))
    }



//    fun sendEmailToUser(email: String) {
//        val context =Context()
//            context.setVariable("emailAddress", email)
//
//    }
}
