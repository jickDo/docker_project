package cloudNative.limjickchan.service

import cloudNative.limjickchan.dto.EmailMessageDto
import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
) {
    fun sendMail(emailMessageDto: EmailMessageDto) {
        val message: MimeMessage = javaMailSender.createMimeMessage()

        val info: String = "<h3 style='color:blue;'>임직찬의 테스트 메일입니다 </h3></br>"

        message.setSubject("[이메일 전송 테스트 폼입니다" + emailMessageDto.subject)
        message.addRecipient(Message.RecipientType.TO, InternetAddress(emailMessageDto.address, "", "UTF-8"))
        message.setText(info, "UTF-8", "html")

        javaMailSender.send(message)
    }
}
