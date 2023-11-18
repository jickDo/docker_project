package cloudNative.limjickchan.service

import cloudNative.limjickchan.access.client.MailAccessClient
import cloudNative.limjickchan.access.client.NewsResponse
import cloudNative.limjickchan.configuration.ContextGeneratorNews
import cloudNative.limjickchan.constants.SecretNaverConst
import cloudNative.limjickchan.repository.EmailRepository
import jakarta.mail.MessagingException
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
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
class NewsService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val emailRepository: EmailRepository,
    private val mailAccessClient: MailAccessClient,
) {
    @Value("\${MAIL_USERNAME}")
    lateinit var myEmail: String
    // 나의 이메일 정보를 Value를 통해 받는다. 여기서 이메일은 환경변수 설정으로 보관된다.
    // 이렇게 설정하는 이유는 이메일은 개인정보이기 때문에 환경변수로 가려두는게 좋기 때문이다.

    fun getNews(): NewsResponse { // 네이버 뉴스 API에 가천대학교와 관련된 뉴스를 가져온다
        return mailAccessClient.getMail(
            SecretNaverConst.X_NAVER_CLIENT_ID, // 네이버 서버로 요청하기 위해서는 별도의 X_NAVER_CLIENT_ID 필요하다
            SecretNaverConst.X_NAVER_CLIENT_SECRET, // 네이버 서버로 요청하기 위해서는 별도의 X_NAVER_CLIENT_SECRET 필요하다.
            "가천대학교", // 이 query를 다른 검색어로 바꾸면 그 검색어에 해당하는 뉴스가 나온다.
            display = 1, // 반환하는 뉴스의 갯수다.
            start = Random.nextInt(1, 101), // 랜덤으로 뉴스를 받는 기능이 없어서, 난수를통해서 임의로 랜덤설정을 한다.
        )
    }

    private fun processNewsInfo(newsInfo: NewsResponse): NewsResponse {
        // HTML 엔티티를 디코드하고 HTML 태그를 제거합니다.
        // 이설정이 없으면 HTML테그가 그대로 반환되어서 디코딩을 통해 제거하는 작업을 거쳐야한다.
        newsInfo.items.forEach { item ->
            item.title = item.title?.let { Jsoup.parse(it).text() }
            item.originallink = item.originallink?.let { Jsoup.parse(it).text() }
            item.link = item.link?.let { Jsoup.parse(it).text() }
            item.description = item.description?.let { Jsoup.parse(it).text() }
            item.pubDate = item.pubDate?.let { Jsoup.parse(it).text() }
        }
        return newsInfo
    }
    fun sendNewsToUser(emailAddress: String) {
        val newsInfo = processNewsInfo(getNews()) // 뉴스를 가져와서 깔끔한 타입으로 뉴스를 바꿔준다.
        val context = ContextGeneratorNews.configureContext(newsInfo) // 구글 smtp는 context타입을 받기때문에 데이터 전처리작업을 거쳐야한다.
        val preparatory = MimeMessagePreparator { mimeMessage ->
            val helper = MimeMessageHelper(mimeMessage, "UTF-8")

            val content = templateEngine.process("news", context) // 이부분은 타임리프 설정으로 news.html에 context를 담는다.
            helper.setTo(emailAddress) // 사용자의 이메일을 설정한다.
            helper.setFrom(myEmail) // 누구에게 오는지를 표시하는 설정, 환경변수로 설정한 나의 이메일 등록
            helper.setSubject("가천대학교 소식기 보기") // 이메일의 제목이다

            helper.setText(content, true) // 아까 설정한 content를 본문에 담고 html을 허용한다.
        }
        try {
            javaMailSender.send(preparatory) // 메일 폼 설정이 끝났기 때문에 전송을한다.
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
}
