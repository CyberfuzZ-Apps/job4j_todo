package ru.job4j.hibernate.manytomany;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс JBook
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "j_book")
public class JBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static JBook of(String name) {
        JBook jBook = new JBook();
        jBook.name = name;
        return jBook;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JBook jBook = (JBook) o;
        return id == jBook.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JBook{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
