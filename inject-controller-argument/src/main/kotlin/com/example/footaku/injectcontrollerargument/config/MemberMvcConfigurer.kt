package com.example.footaku.injectcontrollerargument.config

import com.example.footaku.injectcontrollerargument.repository.MemberRepository
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MemberMvcConfigurer(
    private val memberRepository: MemberRepository
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(MemberArgumentResolver(memberRepository))
    }
}
