package com.mitrais.dbassocdemo.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Book extends BaseAuditModel {

    private String isbn;
    private String title;
    private int totalPages;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(joinColumns = {
                    @JoinColumn(
                            name = "book_id",
                            referencedColumnName = "id"
                    )
               },
               inverseJoinColumns = {
                    @JoinColumn(
                            name = "author_id",
                            referencedColumnName = "id"
                    )
               })
    private Set<Author> authors = new LinkedHashSet<>();

    public void addAuthor(Author author){
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author){
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void removeAllAuthors(){
        authors.forEach(this::removeAuthor);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (totalPages != book.totalPages) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        return title != null ? title.equals(book.title) : book.title == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + totalPages;
        return result;
    }
}
