package cloudNative.limjickchan.example.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class exampleController {

    @GetMapping("/")
    fun getChan(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello good Docker")
    }
}
