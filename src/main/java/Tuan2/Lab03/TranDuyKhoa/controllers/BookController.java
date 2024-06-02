package Tuan2.Lab03.TranDuyKhoa.controllers;
import Tuan2.Lab03.TranDuyKhoa.entities.Book;
import Tuan2.Lab03.TranDuyKhoa.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping
    public String showAllBooks(@NotNull Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(@NotNull Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book/add";
        }
        if(bookService.getBookById(book.getId()).isEmpty())
            bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@NotNull Model model, @PathVariable long id)
    {
        var book = bookService.getBookById(id).orElse(null);
        model.addAttribute("book", book != null ? book : new Book());
        return "book/edit";
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") Book book,BindingResult result) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        if (bookService.getBookById(id).isPresent())
            bookService.deleteBookById(id);
        return "redirect:/books";
    }
    @GetMapping("/timkiem")
    public String searchBooks(@RequestParam("query") String query,Model model) {
        List<Book> books = bookService.searchBooks(query);
        model.addAttribute("books", books);
        return "book/search";
    }
    @RequestMapping(value = "/autocompleteSearch")
    @ResponseBody
    public List<String> autocomplete(@RequestParam(value = "query",required = false,defaultValue = "") String query) {
        return bookService.searchTitles(query);
    }
}
