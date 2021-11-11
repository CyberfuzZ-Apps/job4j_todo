package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Класс HbmRun2
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmRun2 {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        Vacancy vacancy1 = new Vacancy("Java middle job", 2_500.0);
        Vacancy vacancy2 = new Vacancy("Java senior job", 4_375.0);
        Vacancy vacancy3 = new Vacancy("SQL senior job", 4_375.0);
        Vacancy vacancy4 = new Vacancy("JS senior job", 4_375.0);
        VacancyBase vacancyBase1 = new VacancyBase("Vacancy base 1");
        VacancyBase vacancyBase2 = new VacancyBase("Vacancy base 2");
        vacancyBase1.addVacancy(vacancy1);
        vacancyBase1.addVacancy(vacancy2);
        vacancyBase2.addVacancy(vacancy3);
        vacancyBase2.addVacancy(vacancy4);
        Candidate candidate1 = new Candidate(
                "Candidate1",
                "Junior",
                1_250.0,
                vacancyBase1);
        Candidate candidate2 = new Candidate(
                "Candidate2",
                "Middle",
                2_500.0,
                vacancyBase2);
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(vacancy1);
            session.save(vacancy2);
            session.save(vacancy3);
            session.save(vacancy4);
            session.save(vacancyBase1);
            session.save(vacancyBase2);
            session.save(candidate1);
            session.save(candidate2);
            List rsl = session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.vacancyBase base "
                            + "join fetch base.vacancyList vac_list").list();
            System.out.println(rsl);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
