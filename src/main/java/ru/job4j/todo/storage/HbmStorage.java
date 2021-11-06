package ru.job4j.todo.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Item;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Класс HbmStorage - хранилище заданий в базе данных с помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmStorage implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStorage() {
    }

    private static final class Lazy {
        private static final Store INST = new HbmStorage();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Collection<Item> findAll() {
        return transaction(
                session -> session.createQuery("from ru.job4j.todo.model.Item").list()
        );
    }

    @Override
    public void save(Item item) {
        transaction(session -> session.save(item));
    }

    @Override
    public void update(int id) {
        transaction(session -> {
            List items = session.createQuery(
                    "from ru.job4j.todo.model.Item where id = " + id).list();
            Item item = (Item) items.get(0);
            item.setDone(true);
            session.update(item);
            return item;
        });
    }

}
