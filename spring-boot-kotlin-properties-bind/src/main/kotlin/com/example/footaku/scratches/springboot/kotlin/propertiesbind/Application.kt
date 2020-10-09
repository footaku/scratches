package com.example.footaku.scratches.springboot.kotlin.propertiesbind

import com.example.footaku.scratches.springboot.kotlin.propertiesbind.config.Props
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(Props::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
