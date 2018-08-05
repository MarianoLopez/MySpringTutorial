package com.z.bookbackend.services

import com.z.bookbackend.models.Author
import com.z.bookbackend.models.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface AuthorDAO:MongoRepository<Author,String>
interface BookDAO:MongoRepository<Book,String>