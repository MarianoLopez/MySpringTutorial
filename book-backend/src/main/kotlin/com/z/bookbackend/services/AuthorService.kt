package com.z.bookbackend.services

import com.z.bookbackend.models.Author
import com.z.bookbackend.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class AuthorService(private val authorDAO: AuthorDAO, private val bookDAO: BookDAO):BasicCrud<String, Author>{
    override fun getAll(pageable: Pageable): Page<Author>  = authorDAO.findAll(pageable)

    override fun getById(id: String): Optional<Author> = authorDAO.findById(id)

    @Throws(Exception::class)
    override fun insertOrUpdate(obj: Author): Author {
        return when {
            obj.id.isNullOrBlank() -> authorDAO.insert(obj)//new author
            authorDAO.existsById(obj.id) -> authorDAO.insert(obj).apply { //update author
                obj.id?.let { //if does has Id then
                    val booksToUpdate = bookDAO.findByAuthorId(it)//find all his books
                    booksToUpdate.forEach{ it.author = this }//update the books with the current author changes
                    bookDAO.saveAll(booksToUpdate)//save book changes
                }
            }
            else -> throw object : Exception("The author does not exists"){}
        }
    }

    override fun deleteById(id: String): Optional<Author> {
        return authorDAO.findById(id).apply {
            this.ifPresent {
                authorDAO.delete(it)
            }
        }
    }
}