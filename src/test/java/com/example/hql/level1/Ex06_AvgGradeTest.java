package com.example.hql.level1;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Ex06_AvgGradeTest {

    @Test
    public void should_calculate_average_grade() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        Double avg = (Double) session.createQuery("SELECT AVG(e.grade) FROM Enrollment e").uniqueResult();

        assertEquals(true, avg > 14);

        session.getTransaction().commit();
        session.close();
    }
}