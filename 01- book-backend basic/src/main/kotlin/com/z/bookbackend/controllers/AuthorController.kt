package com.z.bookbackend.controllers

import com.z.bookbackend.models.Author
import com.z.bookbackend.services.AuthorService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController//declare this class as rest controller able to catch http request
@RequestMapping("api/author")//controller root path
class AuthorController(private val authorService: AuthorService){//injects bookService by constructor

    @GetMapping fun getAll(pageable: Pageable): Page<Author> = authorService.getAll(pageable)

    @GetMapping("{id}")  fun getById(@PathVariable id:String): Optional<Author> = authorService.getById(id)

    @PostMapping fun insert(@RequestBody author: Author): Author = authorService.insert(author)

    @PutMapping fun update(@RequestBody author: Author): Author = authorService.update(author)

    @DeleteMapping("{id}")  fun deleteById(@PathVariable id: String): Optional<Author>  = authorService.deleteById(id)
}