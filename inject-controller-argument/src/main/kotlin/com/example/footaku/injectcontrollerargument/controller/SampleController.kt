package com.example.footaku.injectcontrollerargument.controller

import com.example.footaku.injectcontrollerargument.model.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sample")
class SampleController {
    @GetMapping
    fun get(member: Member?): String {
        if (member == null) {
            return "null"
        }
        return member.id
    }
}
