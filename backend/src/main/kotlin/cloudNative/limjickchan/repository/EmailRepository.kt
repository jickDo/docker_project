package cloudNative.limjickchan.repository

import cloudNative.limjickchan.entity.Email
import org.springframework.data.jpa.repository.JpaRepository

interface EmailRepository : JpaRepository<Email, Long>
