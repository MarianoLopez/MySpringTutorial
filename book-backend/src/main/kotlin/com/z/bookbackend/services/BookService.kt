package com.z.bookbackend.services

import com.z.bookbackend.models.Book
import com.z.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class BookService(val bookDAO: BookDAO,private val authorDAO: AuthorDAO):BasicCrud<String,Book> {

    override fun getAll(pageable: Pageable): Page<Book> = bookDAO.findAll(pageable)

    override fun getById(id: String): Optional<Book> = bookDAO.findById(id)

    @Throws(Exception::class)
    override fun insertOrUpdate(obj: Book): Book{
        val author = authorDAO.findById(obj.author.id)//re-insert author from db to avoid inconsistency
        return if(author.isPresent){
            obj.author = author.get()
            bookDAO.insert(obj)
        }else{
            throw object: Exception("Author not found"){}
        }
    }

    override fun deleteById(id: String): Optional<Book> {
        return bookDAO.findById(id).apply {
            this.ifPresent { bookDAO.delete(it) }
        }
    }
}