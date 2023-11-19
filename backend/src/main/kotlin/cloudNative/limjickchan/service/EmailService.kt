package cloudNative.limjickchan.service

import cloudNative.limjickchan.access.client.MailAccessClient
import cloudNative.limjickchan.configuration.ContextGeneratorToMe
import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.repository.EmailRepository
import jakarta.mail.MessagingException
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.spring6.SpringTemplateEngine

@Service
@Transactional
class EmailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val emailRepository: EmailRepository,
    private val mailAccessClient: MailAccessClient,
) {

    var myEmail: String = "jickchan0117@gmail.com"

    // 나의 이메일 정보를 Value를 통해 받는다. 여기서 이메일은 환경변수 설정으로 보관된다.
    // 이렇게 설정하는 이유는 이메일은 개인정보이기 때문에 환경변수로 가려두는게 좋기 때문이다.

    fun sendEmailToMe(emailMessageDto: EmailMessageDto) {
        // 나에게 메일을 보내는 메서드이다.
        // 이 메서드의 목적은 사용자가 나를 채용하기 위해 연락목적으로 기획되었다.
        val context = ContextGeneratorToMe.configureContext(emailMessageDto) // 구글 smtp는 context타입을 받기때문에 데이터 전처리작업을 거쳐야한다.
        val preparatory = MimeMessagePreparator { mimeMessage ->
            val helper = MimeMessageHelper(mimeMessage, "UTF-8")

            val content = templateEngine.process("email", context) // 이부분은 타임리프 설정으로 news.html에 context를 담는다.

            helper.setTo(myEmail) // 사용자의 이메일을 설정한다. 나에게 보내야 하기떄문에 나의 이메일을 등록한다.
            helper.setFrom(myEmail) // 누구에게 오는지를 표시하는 설정, 환경변수로 설정한 나의 이메일 등록
            helper.setSubject("당신을 채용하고 싶은 사람이 있어요!") // 이메일의 제목이다

            helper.setText(content, true) // 아까 설정한 content를 본문에 담고 html을 허용한다.
        }
        try {
            javaMailSender.send(preparatory) // 메일 폼 설정이 끝났기 때문에 전송을한다.
            saveMail(emailMessageDto) // 메일을 보낸사람에 대한 정보를 저장한다.
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
    // 메일을 보냈을때 기록용으로 그것을 저장하기 위해서 데이터를 담는다.
}
