package com.example.hql.level3;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Ex22_CaseWhenTest {

    @Test
    public void should_classify_grades() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Object[]> result =
                session.createQuery(
                                "SELECT s.firstName, " +
                                        "CASE " +
                                        "WHEN e.grade >= 16 THEN 'Excellent' " +
                                        "WHEN e.grade >= 12 THEN 'Good' " +
                                        "ELSE 'Average' END " +
                                        "FROM Enrollment e JOIN e.student s")
                        .list();

        assertTrue(result.size() > 0);

        session.getTransaction().commit();
        session.close();
    }
}

