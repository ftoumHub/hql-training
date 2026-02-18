package com.example.hql;

import com.example.hql.dto.StudentCourseDTO;
import com.example.hql.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HqlPractice {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ex3_join(session);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public static void ex1_selectSimple(Session session) {
        System.out.println("\n1) - SELECT simple");
        List<Student> students = session.createQuery("FROM Student").list();
        System.out.println(students);
    }

    private static void ex2_where(Session session) {
        System.out.println("\n2) - WHERE");
        List<Student> adults = session.createQuery("FROM Student s WHERE s.age > 26").list();
        System.out.println(adults);
    }

    private static void ex3_join(Session session) {
        System.out.println("\n3) - JOIN");
        List<StudentCourseDTO> results =
                session.createQuery(
                                "SELECT new com.example.hql.dto.StudentCourseDTO(s.firstName, c.title) " +
                                        "FROM Enrollment e " +
                                        "JOIN e.student s " +
                                        "JOIN e.course c")
                        .list();
        System.out.println(results);
    }

    private static void ex4_groupBy(Session session) {
        System.out.println("\n4) - GROUP BY");
        List<Object[]> avgGrades =
                session.createQuery(
                                "SELECT s.firstName, AVG(e.grade) " +
                                        "FROM Enrollment e JOIN e.student s " +
                                        "GROUP BY s.firstName")
                        .list();
    }

    private static void ex5_updateHql(Session session) {
        System.out.println("\n5) - UPDATE HQL");
        session.createQuery("UPDATE Student s SET s.age = s.age + 1").executeUpdate();
    }
}
