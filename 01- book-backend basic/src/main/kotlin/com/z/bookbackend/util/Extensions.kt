package com.z.bookbackend.util

import java.time.LocalDate

fun String.toLocalDate() = LocalDate.parse(this, Objects.dateFormat)