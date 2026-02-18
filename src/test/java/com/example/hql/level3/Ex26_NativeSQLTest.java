package com.example.hql.level3;

import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Ex26_NativeSQLTest {

    @Test
    public void should_execute_native_sql() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        List<String> result = session.createSQLQuery("SELECT first_name FROM student").list();

        assertTrue(!result.isEmpty());

        session.getTransaction().commit();
        session.close();
    }
}

