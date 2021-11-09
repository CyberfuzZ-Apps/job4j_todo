package ru.job4j.hibernate.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.manytomany.JAuthor;
import ru.job4j.hibernate.manytomany.JBook;

import java.util.List;

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

            Engine engine1 = Engine.of("OHC");
            Engine engine2 = Engine.of("Diesel");

            Driver driver1 = Driver.of("Ivan");
            Driver driver2 = Driver.of("Petr");

            Car car1 = Car.of("BMW", engine1);
            Car car2 = Car.of("TOYOTA", engine2);

            car1.addDriver(driver1);
            car1.addDriver(driver2);
            car2.addDriver(driver1);

            session.persist(engine1);
            session.persist(engine2);
            session.persist(driver1);
            session.persist(driver2);
            session.persist(car1);
            session.persist(car2);

            List rsl = session.createQuery("from Car ").list();
            System.out.println(rsl);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
