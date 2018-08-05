package com.z.bookbackend.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate() = LocalDate.parse(this, DateTimeFormatter.ofPattern("dd-MM-yyyy"))