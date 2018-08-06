package com.z.bookbackend.controllers

import com.z.bookbackend.models.Book
import com.z.bookbackend.services.BookDAO
import com.z.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/book")
class BookController (private val bookDAO: BookDAO): BasicCrud<String, Book> {

    @GetMapping override fun getAll(pageable: Pageable): Page<Book> = bookDAO.findAll(pageable)

    @GetMapping("{id}") override fun getById(@PathVariable id:String): Optional<Book> = bookDAO.findById(id)

    @PostMapping override fun insert(@RequestBody obj: Book): Book = bookDAO.insert(obj)

    @PutMapping override fun update(@RequestBody obj: Book): Book = bookDAO.insert(obj) //TODO: re-insert author from db to avoid inconsistency

    @DeleteMapping("{id}") override fun deleteById(id: String): Optional<Book> {
        return bookDAO.findById(id).apply {
            this.ifPresent { bookDAO.delete(it) }
        }
    }
}