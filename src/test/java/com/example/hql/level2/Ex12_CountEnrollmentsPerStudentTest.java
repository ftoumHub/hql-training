package com.example.hql.level2;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Ex12 – GROUP BY (nombre d’inscriptions par étudiant)
 */
public class Ex12_CountEnrollmentsPerStudentTest {

    @Test
    public void should_count_enrollments_per_student() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Object[]> result =
                session.createQuery(
                                "SELECT s.firstName, COUNT(e) " +
                                        "FROM Enrollment e " +
                                        "JOIN e.student s " +
                                        "GROUP BY s.firstName")
                        .list();

        assertEquals(3, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
