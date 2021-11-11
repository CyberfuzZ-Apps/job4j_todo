package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;

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
        Candidate candidate1 = new Candidate("Candidate1", "Junior", 1_250.0);
        Candidate candidate2 = new Candidate("Candidate2", "Middle", 2_500.0);
        Candidate candidate3 = new Candidate("Candidate3", "Senior", 4_375.0);
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            session.save(candidate1);
            session.save(candidate2);
            session.save(candidate3);
            /* Find all */
            Query query = session.createQuery("from Candidate ");
            for (Object can : query.getResultList()) {
                System.out.println(can);
            }
            /* Find by id */
            Object query1 = session.createQuery("from Candidate c where c.id = :cId")
                    .setParameter("cId", 1).uniqueResult();
            System.out.println(query1);
            /* Find by name */
            Object query2 = session.createQuery("from Candidate c where c.name = :cName")
                    .setParameter("cName", "Candidate3").uniqueResult();
            System.out.println(query2);
            /* Update */
            session.createQuery(
                    "update Candidate c set c.experience = :cExp, c.salary = :cSalary "
                            + "where c.id = :cId")
                    .setParameter("cExp", "TeamLead")
                    .setParameter("cSalary", 6_250.0)
                    .setParameter("cId", 3)
                    .executeUpdate();
            /* Delete */
            session.createQuery("delete from Candidate c where c.id = :cId")
                    .setParameter("cId", 1)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
