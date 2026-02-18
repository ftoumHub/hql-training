package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex01_SelectAllStudentsTest {

    @Test
    public void should_return_all_students() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result = session.createQuery("FROM Student").list();

        assertEquals(4, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
