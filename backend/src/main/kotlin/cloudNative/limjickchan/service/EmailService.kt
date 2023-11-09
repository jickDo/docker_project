package cloudNative.limjickchan.service

import cloudNative.limjickchan.dto.EmailMessageDto
import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender,
) {

    @Value("\${spring.mail.username}")
    lateinit var myEmail: String
    fun sendMail(emailMessageDto: EmailMessageDto) {
        val message: MimeMessage = javaMailSender.createMimeMessage()

        val subject = "<h3 style='color:blue;'>" + emailMessageDto.name + "이 보낸 메일입니다" + "</h3></br>"
        val comment = "<h3 style='color:blue;'> 직업: " + emailMessageDto.job + "<br><br>" + "<h3 style='color:blue;'> 주소: " + emailMessageDto.address + "<br><br>" + "<h3 style='color:blue;'> 회사 사이트 주소: " + emailMessageDto.companyUrl + "<br><br>"

        message.setSubject("[이메일 전송 테스트 폼입니다$subject")
        message.addRecipient(Message.RecipientType.TO, InternetAddress(myEmail, "", "UTF-8"))
        message.setText(comment, "UTF-8", "html")

        javaMailSender.send(message)
    }
}
