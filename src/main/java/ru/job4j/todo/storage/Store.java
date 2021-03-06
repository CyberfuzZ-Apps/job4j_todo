package ru.job4j.todo.storage;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.Collection;

/**
 * Интерфейс Store - определяет методы для хранилища.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store {

    Collection<Item> findAll();

    Collection<Category> findAllCategories();

    User findUserByEmail(String email);

    void save(Item item, String[] categories);

    void save(User user);

    void update(int id);
}
