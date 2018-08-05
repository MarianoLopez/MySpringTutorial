package com.z.bookbackend.controllers

import com.z.bookbackend.models.Author
import com.z.bookbackend.services.AuthorDAO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/author")
class AuthorController(private val authorDAO: AuthorDAO) {
    @GetMapping fun getAll(pageable: Pageable): Page<Author> = authorDAO.findAll(pageable)

    @GetMapping("{id}") fun getById(@PathVariable id:String): Optional<Author> = authorDAO.findById(id)

    @PostMapping fun insert(@RequestBody author: Author): Author = authorDAO.insert(author)
}