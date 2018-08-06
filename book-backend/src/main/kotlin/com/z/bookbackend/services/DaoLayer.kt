package com.z.bookbackend.services

import com.z.bookbackend.models.Author
import com.z.bookbackend.models.Book
import org.springframework.data.mongodb.repository.MongoRepository

/*Spring does the implementation for us*/
interface AuthorDAO:MongoRepository<Author,String>
interface BookDAO:MongoRepository<Book,String>{
    fun findByAuthorId(id:String):List<Book>
    fun findByNameRegex(name:String):List<Book>
}