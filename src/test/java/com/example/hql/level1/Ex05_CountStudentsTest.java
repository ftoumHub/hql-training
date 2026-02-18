package com.example.hql.level1;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Ex05_CountStudentsTest {

    @Test
    public void test() {
        Session s = HibernateTestUtil.getSession();
        s.beginTransaction();

        Long count = (Long) s.createQuery("SELECT COUNT(s) FROM Student s").uniqueResult();

        assertEquals(Long.valueOf(3), count);

        s.getTransaction().commit();
        s.close();
    }
}

