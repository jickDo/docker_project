package cloudNative.limjickchan.repository

import cloudNative.limjickchan.entity.Email
import org.springframework.data.jpa.repository.JpaRepository

interface emailRepository : JpaRepository<Email, Long>
