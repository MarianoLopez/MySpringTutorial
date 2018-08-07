package com.z.bookbackend.controllers

import com.z.bookbackend.models.Author
import com.z.bookbackend.services.AuthorService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController//declare this class as rest controller able to catch http request
@RequestMapping("api/author")//controller root path
class AuthorController(private val authorService: AuthorService){//injects bookService by constructor

    @ApiImplicitParams(ApiImplicitParam(name = "Pageable",value = "Optional pageable request: page=&size=&sort=example.ASC|DESC",required = false, dataType = "string", paramType = "query"))
    @ApiOperation(value = "Get all authors",produces = "application/json;charset=UTF-8")
    @GetMapping fun getAll(pageable: Pageable): Page<Author> = authorService.getAll(pageable)

    @ApiOperation(value = "Get author by id",produces = "application/json;charset=UTF-8")
    @GetMapping("{id}")  fun getById(@PathVariable id:String): Optional<Author> = authorService.getById(id)

    @ApiOperation(value = "Insert (id must be null) an author",produces = "application/json;charset=UTF-8")
    @PostMapping fun insert(@RequestBody author: Author): Author = authorService.insert(author)

    @ApiOperation(value = "Update (id must not be null) an author",produces = "application/json;charset=UTF-8")
    @PutMapping fun update(@RequestBody author: Author): Author = authorService.update(author)

    @ApiOperation(value = "Delete author by id (will delete his books in cascade)",produces = "application/json;charset=UTF-8")
    @DeleteMapping("{id}")  fun deleteById(@PathVariable id: String): Optional<Author>  = authorService.deleteById(id)
}