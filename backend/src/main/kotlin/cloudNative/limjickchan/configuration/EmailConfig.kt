package cloudNative.limjickchan.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EmailConfig {
    @Bean
    fun emailSwaggerApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Email")
            .pathsToMatch("/limjickchan/**")
            .pathsToExclude("")
            .packagesToScan("cloudNative.limjickchan.controller")
            .build()
    }
}
