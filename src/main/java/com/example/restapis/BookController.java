package com.example.restapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private static Logger logger= LoggerFactory.getLogger(BookController.class);
    //    1] GET : Retrieve some information from server.
//    2] POST : Saving some information on server side.
//    3] DELETE : Deleting the information on the server.
//    4] PUT : Updating the already existing information on the server.

    HashMap<String, Book> bookHashMap = new HashMap<>();

    // localhost:8080/book?bid=1
    //Based on the book id passed, we want to return a book object
    @GetMapping("/book")
    public Book getBook(@RequestParam("bid") String bid){
        logger.info("received a book id {}",bid);
        return bookHashMap.get(bid);
    }

//    @PostMapping("/book")
//    //We want to store a book so this is one way to take the book properties from the frontend but the way is not scalable
//    public void insertBook(@RequestParam Integer bid,
//                           @RequestParam String name,
//                           @RequestParam String author,
//                           @RequestParam Integer cost){
//
//    }


    //We are putting id as a key in the hashmap
//    @PostMapping("/book")
//    public void insertBook(@RequestBody Book book){
//
//        logger.info("Book received:{}",book);
//        bookHashMap.put(book.getId(),book);
//
//    }
    @PostMapping("/book")
    public void insertBook(@RequestBody Book book){
        logger.info("Book received:{}",book);
        bookHashMap.put(book.getId(),book);


    }
//    @PostMapping("/book")
//    public void insertBook(@RequestBody BookAuthor book){
//        logger.info("Book received:{}",book);
//        bookHashMap.put(book.getId(),book);


    //For reading details of all the books or to get all the books.
    @GetMapping("/book/all")
    //We want to get a book list so list of books
    public List<Book> getBooks(){
        return bookHashMap.values().stream().collect(Collectors.toList());
    }
    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestParam String bookId){
        bookHashMap.put(bookId, book);
        return bookHashMap.get(bookId);
    }

    @DeleteMapping("/book")
    public Book deleteBook(@RequestParam("bid") String bookId){
        return bookHashMap.remove(bookId);
    }

    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable("bookId") String id){
        return  bookHashMap.get(id);
    }

//    @GetMapping("/book/{bookId}/{message}")
//    public Book getBookById(@PathVariable("bookId") String id, @PathVariable("message") String message){
//        logger.info(message);
//        return  bookHashMap.get(id);
//    }

    //url will look like for below case: localhost:8080/book/Hello?bookId=1
    //This api is not working for me in the browser
    @GetMapping("/book/{message}")
    public Book getBookById(@RequestParam("bookId") String id, @PathVariable("message") String message){
        logger.info(message);
        return  bookHashMap.get(id);
    }

    //When we do not want to pass the RequestParam as a part of Url.
    //This api is not working for me
//    @GetMapping("/get-book")
//    public Book getBookWithOptional(@RequestParam(value = "bid", required = false, defaultValue = "2") String bid){
//        logger.info("received a book id {}",bid);
//        return bookHashMap.get(bid);
//    }



}



