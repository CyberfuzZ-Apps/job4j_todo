package ru.job4j.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Класс HbmRun
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            JAuthor author1 = JAuthor.of("Кэтти Сиерра");
            JAuthor author2 = JAuthor.of("Роберт Лафоре");
            JAuthor author3 = JAuthor.of("Неизвестный автор");

            JBook book1 = JBook.of("Java");
            JBook book2 = JBook.of("Algorithms");
            JBook book3 = JBook.of("Неизвестная книга");

            author1.getBooks().add(book1);
            author2.getBooks().add(book2);

            author3.getBooks().add(book1);
            author3.getBooks().add(book2);
            author3.getBooks().add(book3);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);

            JAuthor author = session.get(JAuthor.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
