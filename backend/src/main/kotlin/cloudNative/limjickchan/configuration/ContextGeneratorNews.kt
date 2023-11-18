package cloudNative.limjickchan.configuration

import cloudNative.limjickchan.access.client.NewsResponse
import org.thymeleaf.context.Context

object ContextGeneratorNews {
    // 생성한 뉴스를 Context에 담는 로직이다..
    // 따로 빼놓은 이유는 메인로직에 크게 중요하지 않는 부가로직이며, 추후 코드 재사용에서도 유용하기 때문이다.
    fun configureContext(newsResponse: NewsResponse): Context {
        val context = Context()
        newsResponse.items.forEach {
            context.setVariable("title", it.title)
            context.setVariable("originallink", it.originallink)
            context.setVariable("link", it.link)
            context.setVariable("description", it.description)
            context.setVariable("pubDate", it.pubDate)
        }
        return context
    }
}
