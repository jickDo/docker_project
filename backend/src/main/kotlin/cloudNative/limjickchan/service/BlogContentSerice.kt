package cloudNative.limjickchan.service

import cloudNative.limjickchan.entity.Content
import cloudNative.limjickchan.repository.blogContentRepository
import org.springframework.stereotype.Service

@Service
class BlogContentSerice(
    val blogContentRepository: blogContentRepository,
) {
    fun createBlogContent(content: Content): Long {
        blogContentRepository.save(content)
        return content.id
    }

    fun getBlogContent() {
    }
}
