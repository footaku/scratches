package com.example.footaku.injectcontrollerargument.config

import com.example.footaku.injectcontrollerargument.model.Member
import com.example.footaku.injectcontrollerargument.repository.MemberRepository
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class MemberArgumentResolver(
    private val memberRepository: MemberRepository
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        Member::class.java.isAssignableFrom(parameter.parameterType)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Member? {
        val token = webRequest.getHeaderValues("member_token")
            ?: return null

        val nonNullToken = token
            .takeIf { it.isNotEmpty() }
            ?.first()
            ?: return null

        return memberRepository.get(nonNullToken)
    }
}
