package com.mlaino.examples.openapi.demo

import com.mlaino.examples.openapi.apis.server.BooksApiController
import org.openapitools.model.Book
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BookApi : BooksApiController() {
    override fun booksGet(): ResponseEntity<List<Book>> {
        return ResponseEntity.ok(
            listOf(
                Book("Isaac Asimov", "Foundation"),
                Book("Dune", "Frank Herbert"),
                Book("Hyperion", "Dan Simmons"),
                Book("At the Mountains of Madness", "H.P. Lovecraft"),
                Book("The Lord of the Rings", "J.R.R. Tolkien"),
            )
        )
    }
}