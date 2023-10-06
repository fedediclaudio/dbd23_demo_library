package com.dbd23.demo1.library.controllers;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/author")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/prueba")
    public String prueba() {
        return "OK";
    }

    @GetMapping(path = "/{id}")
    public Author getAuthorById(@PathVariable Long id) throws LibraryException {
        return this.authorService.getById(id).orElse(null);
    }

    @PostMapping(path = "")
    public Author postAuthor(@RequestBody Author author) throws LibraryException {
        return this.authorService.createAuthor(author.getFullname(), author.getDateOfBirth());
    }

    @PutMapping(path = "")
    public Author putAuthor(@RequestBody Author author) throws LibraryException {
        return this.authorService.updateAuthor(author);
    }
}
