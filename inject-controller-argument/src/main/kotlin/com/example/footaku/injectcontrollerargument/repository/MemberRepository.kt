package com.example.footaku.injectcontrollerargument.repository

import com.example.footaku.injectcontrollerargument.model.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepository {
    fun get(token : String): Member = Member(token)
}
