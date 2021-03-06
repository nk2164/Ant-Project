package com.nk2164.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nk2164.domain.Book;
import com.nk2164.services.BookService;

/**
 * A Controller to handle the creation of a book, including the Form Management
 * (re-presentation of forms)
 */
@Controller
@RequestMapping("/addNewBook")
public class CreateBookController 
{
	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("add-new-book", "book", new Book());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(@Valid Book newBook, Errors result) 
	{
		if (result.hasErrors())
		{
			return new ModelAndView("add-new-book", "book", newBook);
		}
		bookService.registerNewBook(newBook);
		return new ModelAndView("book-added", "title", newBook.getTitle());
	}

}
