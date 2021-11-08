package ru.job4j.hibernate.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс JAuthor
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "j_author")
public class JAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JBook> books = new ArrayList<>();

    public static JAuthor of(String name) {
        JAuthor jAuthor = new JAuthor();
        jAuthor.name = name;
        return jAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JBook> getBooks() {
        return books;
    }

    public void setBooks(List<JBook> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JAuthor jAuthor = (JAuthor) o;
        return id == jAuthor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JAuthor{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", books=" + books
                + '}';
    }
}
