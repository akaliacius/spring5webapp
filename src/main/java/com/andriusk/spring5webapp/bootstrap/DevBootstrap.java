package com.andriusk.spring5webapp.bootstrap;

import com.andriusk.spring5webapp.model.Author;
import com.andriusk.spring5webapp.model.Book;
import com.andriusk.spring5webapp.model.Publisher;
import com.andriusk.spring5webapp.repositories.AuthorRepository;
import com.andriusk.spring5webapp.repositories.BookRepository;
import com.andriusk.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher collins = new Publisher("Harper Collins", "United States of America");
        Book ddd = new Book("Domain Driven Design", "1234", collins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(collins);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "United States of America");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        publisherRepository.save(worx);
        bookRepository.save(noEJB);
    }
}
