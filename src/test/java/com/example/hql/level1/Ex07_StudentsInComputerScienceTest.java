package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex07_StudentsInComputerScienceTest {

    @Test
    public void should_return_students_in_cs() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery(
                                "SELECT s FROM Enrollment e " +
                                        "JOIN e.student s " +
                                        "JOIN e.course c " +
                                        "WHERE c.title = :title")
                        .setParameter("title", "Computer Science")
                        .list();

        assertEquals(2, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
