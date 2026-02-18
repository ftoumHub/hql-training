package com.example.hql.level1;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Ex04_SelectFirstNamesTest {

    @Test
    public void test() {
        Session s = HibernateTestUtil.getSession();
        s.beginTransaction();

        List<String> names = s.createQuery("SELECT s.firstName FROM Student s").list();

        assertTrue(names.contains("Alice"));

        s.getTransaction().commit();
        s.close();
    }
}
