package cloudNative.limjickchan.access.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class MailConfig {
    // 이 부분은 스프링 6에서 나온 HTTP Interface를 사용하기 위해서 빈으로 등록하는 설정이다.
    // HTTP Interface는 외부 서버와 통신하기 위한 목적이며, 유사한 기술로는 restTemplate와 webClient가 있다.
    // HTTP Interface를 설정한 이유는 신규 기술이며, 가독성 측면에서 뛰어나기 때문이다.
    @Bean
    internal fun OpenApiClient(): MailAccessClient {
        val webClient = WebClient.builder().build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(MailAccessClient::class.java)
    }
}