package com.xct.bootdemo.controller

import com.xct.bootdemo.beans.Result
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
open class TestController {

    @GetMapping
    fun test(): Result{
        val result = Result(9)
        result.msg = "kotlin test request"
        return result
    }
}