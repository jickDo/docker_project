package cloudNative.limjickchan.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    // CORS설정이다. 매우 정형화 된 설정이라 설명은 생략하겠다.
    // 참고로 모든 경우에 대한 허용을 해두었고, 그렇게 한 이유는 실제 운용할 서비스가 아니라는 이유에서이다.
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(false).maxAge(6000)
    }
}
