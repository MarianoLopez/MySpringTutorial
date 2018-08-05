package com.z.bookbackend.controllers

import com.z.bookbackend.models.Book
import com.z.bookbackend.services.BookDAO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/book")
class BookController (private val bookDAO: BookDAO){
    @GetMapping fun getAll(pageable: Pageable): Page<Book> = bookDAO.findAll(pageable)
    @GetMapping("{isbn}") fun getByISBN(@PathVariable isbn:String): Optional<Book> = bookDAO.findById(isbn)
    @PostMapping fun insert(@RequestBody book:Book): Book = bookDAO.insert(book)
}