package com.example.hql.level2;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Ex13 – HAVING - Étudiants avec plus d’une inscription.
 */
public class Ex13_HavingMoreThanOneEnrollmentTest {

    @Test
    public void should_return_students_with_more_than_one_enrollment() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Object[]> result =
                session.createQuery(
                                "SELECT s.firstName, COUNT(e) " +
                                        "FROM Enrollment e " +
                                        "JOIN e.student s " +
                                        "GROUP BY s.firstName " +
                                        "HAVING COUNT(e) > 1")
                        .list();

        assertEquals(1, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
