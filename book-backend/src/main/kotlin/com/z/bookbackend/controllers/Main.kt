package com.z.bookbackend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController{
    @GetMapping
    fun index()="Hello World"
}