package com.example.hql.level2;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Ex11 – Étudiants ayant une note > 15
 */
public class Ex11_StudentsWithGradeGreaterThan15Test {

    @Test
    public void should_return_students_with_high_grades() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery(
                                "SELECT DISTINCT s FROM Enrollment e " +
                                        "JOIN e.student s " +
                                        "WHERE e.grade > :grade")
                        .setParameter("grade", 15.0)
                        .list();

        assertEquals(1, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
