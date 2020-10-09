package com.example.footaku.scratches.springboot.kotlin.propertiesbind.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "props")
data class Props(
    val settings: List<String>,
    val item: Item
) {
    data class Item(
        val subItem1: Sub,
        val subItem2: Sub
    ) {
        data class Sub(val value: String)
    }
}
