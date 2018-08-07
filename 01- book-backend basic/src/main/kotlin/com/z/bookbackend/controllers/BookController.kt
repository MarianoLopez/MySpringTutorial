package com.z.bookbackend.controllers

import com.z.bookbackend.models.Book
import com.z.bookbackend.services.BookService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController//declare this class as rest controller able to catch http request
@RequestMapping("api/book")//controller root path
class BookController (private val bookService: BookService) {//injects bookService by constructor


    @GetMapping fun getAll(pageable: Pageable): Page<Book> = bookService.getAll(pageable)


    @GetMapping("{isbn}") fun getByIsbn(@PathVariable isbn:String): Optional<Book> = bookService.getById(isbn)


    @GetMapping("/byName/{regex}") fun getByName(@PathVariable regex:String):List<Book> = bookService.bookDAO.findByNameRegex(regex)


    @PostMapping fun insert(@RequestBody book: Book): Book = bookService.insert(book)


    @PutMapping fun update(@RequestBody book: Book): Book = bookService.update(book)


    @DeleteMapping("{isbn}")  fun deleteByIsbn(@PathVariable isbn: String): Optional<Book> = bookService.deleteById(isbn)
}