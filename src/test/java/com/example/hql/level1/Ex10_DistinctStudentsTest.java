package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex10_DistinctStudentsTest {

    @Test
    public void should_return_distinct_students() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery(
                                "SELECT DISTINCT s FROM Student s JOIN s.enrollments e")
                        .list();

        assertEquals(3, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
