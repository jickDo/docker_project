package cloudNative.limjickchan.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000") // 프론트엔드의 출처만 허용
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // "UPDATE"는 표준 메서드가 아닙니다.
            .allowedHeaders("*") // 필요한 경우 특정 헤더를 명시
            .allowCredentials(true)
            .maxAge(3600)
    }
}
