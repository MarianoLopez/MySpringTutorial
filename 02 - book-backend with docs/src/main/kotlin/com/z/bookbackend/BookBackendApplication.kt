package com.z.bookbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookBackendApplication

fun main(args: Array<String>) {
    runApplication<BookBackendApplication>(*args)
}