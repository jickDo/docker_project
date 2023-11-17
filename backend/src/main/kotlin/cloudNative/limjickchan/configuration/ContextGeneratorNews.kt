package cloudNative.limjickchan.configuration

import cloudNative.limjickchan.access.client.NewsResponse
import org.thymeleaf.context.Context

object ContextGeneratorNews {
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
