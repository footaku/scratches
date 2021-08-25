package com.example.footaku.customhealthendpoint.controller

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthComponent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RestControllerEndpoint(id = "custom")
class CustomHealthCheckRestController {
    @GetMapping("health")
    fun health(): ResponseEntity<HealthComponent> {
        return ResponseEntity(Health.Builder().up().build(), HttpStatus.ACCEPTED)
    }
}

@Component
@WebEndpoint(id = "custom-health")
class CustomHealthCheckWebEndpoint {
    @ReadOperation
    fun health(): ResponseEntity<HealthComponent> {
        return ResponseEntity(Health.Builder().up().build(), HttpStatus.ACCEPTED)
    }
}
