package com.z.bookbackend.util

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BasicCrud<K,T> {
    fun getAll(pageable: Pageable): Page<T>
    fun getById(id:K):Optional<T>
    fun insertOrUpdate(obj:T):T
    fun deleteById(id: K):Optional<T>
}