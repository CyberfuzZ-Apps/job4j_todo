package ru.job4j.hibernate.lazyinitializationexception;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
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
        List<JCarBrand> list = new ArrayList<>();
        JCarBrand brand = JCarBrand.of("Hammer");
        JCarModel model1 = JCarModel.of("H1", brand);
        JCarModel model2 = JCarModel.of("H2", brand);
        JCarModel model3 = JCarModel.of("H3", brand);
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            session.save(brand);
            session.save(model1);
            session.save(model2);
            session.save(model3);

            /* Первый способ: в той же сессии

            list = session.createQuery("from JCarBrand ").list();
            for (JCarBrand jCarBrand : list) {
                for (JCarModel model : jCarBrand.getModelList()) {
                    System.out.println(model);
                }
            }
             */

            /* Второй способ: join fetch */
            list = session.createQuery(
                    "select distinct b from JCarBrand b join fetch b.modelList").list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (JCarBrand jCarBrand : list) {
            for (JCarModel model : jCarBrand.getModelList()) {
                System.out.println(model);
            }
        }
    }
}
