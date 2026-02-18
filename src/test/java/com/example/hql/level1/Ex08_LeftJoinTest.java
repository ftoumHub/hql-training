package com.example.hql.level1;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex08_LeftJoinTest {

    @Test
    public void should_left_join_students_and_enrollments() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Object[]> result =
                session.createQuery(
                                "SELECT s, e FROM Student s LEFT JOIN s.enrollments e")
                        .list();

        assertEquals(true, result.size() >= 3);

        session.getTransaction().commit();
        session.close();
    }
}
