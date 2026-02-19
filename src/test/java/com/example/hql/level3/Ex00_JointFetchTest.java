package com.example.hql.level3;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex00_JointFetchTest {

    @Test
    public void should_return_all_students() {

        SessionFactory sf = HibernateTestUtil.getSessionFactory();
        sf.getStatistics().clear();
        sf.getStatistics().setStatisticsEnabled(true);

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result = session.createQuery(
                "SELECT DISTINCT s " +
                "FROM Student s " +
                "LEFT JOIN FETCH s.enrollments e " +
                "LEFT JOIN FETCH e.course").list();

        assertEquals(4, result.size());



        long queryCount = sf.getStatistics().getQueryExecutionCount();
        System.out.println("SQL count = " + queryCount);

        session.getTransaction().commit();
        session.close();
    }
}
