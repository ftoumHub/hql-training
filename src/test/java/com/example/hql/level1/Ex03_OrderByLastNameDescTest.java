package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex03_OrderByLastNameDescTest {

    @Test
    public void should_order_students_desc() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery(
                                "FROM Student s ORDER BY s.lastName DESC")
                        .list();

        assertEquals(3, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
