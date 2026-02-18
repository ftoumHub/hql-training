package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex02_StudentsOlderThan22Test {

    @Test
    public void should_return_students_older_than_22() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery(
                                "FROM Student s WHERE s.age > :age")
                        .setParameter("age", 22)
                        .list();

        assertEquals(1, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
