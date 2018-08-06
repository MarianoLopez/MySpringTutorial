package com.z.bookbackend.controllers

import com.z.bookbackend.models.Author
import com.z.bookbackend.services.AuthorDAO
import com.z.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/author")
class AuthorController(private val authorDAO: AuthorDAO):BasicCrud<String,Author> {

    @GetMapping override fun getAll(pageable: Pageable): Page<Author> = authorDAO.findAll(pageable)

    @GetMapping("{id}") override fun getById(@PathVariable id:String): Optional<Author> = authorDAO.findById(id)

    @PostMapping override fun insert(@RequestBody obj: Author): Author{
        return if(obj.id.isNullOrBlank()){
            authorDAO.insert(obj)
        }else{
            throw object : Exception("ID must be null or empty"){}
        }
    }


    @PutMapping override fun update(obj: Author): Author {
        return if(obj.id.isNullOrBlank()){
            throw object : Exception("ID must not be null or empty"){}
        }else{
            authorDAO.insert(obj) //TODO: update all books of the updated author
        }
    }

    @DeleteMapping override fun deleteById(id: String): Optional<Author> {
        return authorDAO.findById(id).apply {
            this.ifPresent { authorDAO.delete(it) } //TODO: delete all books of the deleted author
        }
    }
}