package ru.job4j.todo.storage;

import ru.job4j.todo.model.Item;

import java.util.Collection;

/**
 * Интерфейс Store - определяет методы для хранилища.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store {

    Collection<Item> findAll();

    void save(Item item);

    void update(int id);
}
