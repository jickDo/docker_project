package cloudNative.limjickchan.repository

import cloudNative.limjickchan.entity.Content
import org.springframework.data.jpa.repository.JpaRepository

interface blogContentRepository : JpaRepository<Content, Long>
