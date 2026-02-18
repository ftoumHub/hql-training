package com.example.hql.level1;

import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Ex09_PaginationTest {

    @Test
    public void should_paginate_students() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<Student> result =
                (List<Student>) session.createQuery("FROM Student")
                        .setFirstResult(0)
                        .setMaxResults(2)
                        .list();

        assertEquals(2, result.size());

        session.getTransaction().commit();
        session.close();
    }
}
