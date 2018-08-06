package com.z.bookbackend.controllers

import com.z.bookbackend.models.Book
import com.z.bookbackend.services.BookService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController//declare this class as rest controller able to catch http request
@RequestMapping("api/book")//controller root path
class BookController (private val bookService: BookService) {//injects bookService by constructor

    @ApiImplicitParams(ApiImplicitParam(name = "Pageable",value = "Optional pageable request: page=&size=&sort=example.ASC|DESC",required = false, dataType = "string", paramType = "query"))
    @ApiOperation(value = "Get all books",produces = "application/json;charset=UTF-8")
    @GetMapping fun getAll(pageable: Pageable): Page<Book> = bookService.getAll(pageable)

    @ApiOperation(value = "Get book by isbn",produces = "application/json;charset=UTF-8")
    @GetMapping("{isbn}") fun getByIsbn(@PathVariable isbn:String): Optional<Book> = bookService.getById(isbn)

    @ApiOperation(value = "Get book by name regex",produces = "application/json;charset=UTF-8")
    @GetMapping("/byName/{regex}") fun getByName(@PathVariable regex:String):List<Book> = bookService.bookDAO.findByNameRegex(regex)

    @ApiOperation(value = "Insert a book",produces = "application/json;charset=UTF-8")
    @PostMapping fun insert(@RequestBody book: Book): Book = bookService.insert(book)

    @ApiOperation(value = "Update a book",produces = "application/json;charset=UTF-8")
    @PutMapping fun update(@RequestBody book: Book): Book = bookService.update(book)

    @ApiOperation(value = "Delete book by isbn",produces = "application/json;charset=UTF-8")
    @DeleteMapping("{isbn}")  fun deleteByIsbn(@PathVariable isbn: String): Optional<Book> = bookService.deleteById(isbn)
}