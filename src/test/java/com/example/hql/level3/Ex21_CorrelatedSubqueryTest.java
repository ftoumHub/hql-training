package com.example.hql.level3;

import com.example.hql.entity.Enrollment;
import com.example.hql.entity.Student;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

/**
 * Étudiants ayant une note supérieure à leur propre moyenne.
 */
public class Ex21_CorrelatedSubqueryTest {

    @Test
    public void should_return_students_above_their_average() {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery(
                "SELECT DISTINCT s FROM Enrollment e " +
                        "JOIN e.student s " +
                        "WHERE e.grade > (" +
                        "SELECT AVG(e2.grade) FROM Enrollment e2 " +
                        "WHERE e2.student = s)");

        List<Student> result = list(query);

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getFirstName());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void should_return_students_above_their_average_with_stream() {

        SessionFactory sf = HibernateTestUtil.getSessionFactory();
        sf.getStatistics().clear();
        sf.getStatistics().setStatisticsEnabled(true);

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery(
                "SELECT DISTINCT e " +
                        "FROM Enrollment e " +
                        "LEFT JOIN FETCH e.student " +
                        "LEFT JOIN FETCH e.course");

        List<Enrollment> enrollments = list(query);

        assertEquals(4, enrollments.size());

        long queryCount = sf.getStatistics().getQueryExecutionCount();
        System.out.println("SQL count = " + queryCount);

        session.getTransaction().commit();
        session.close();

        Map<String, Double> averageByStudent =
                enrollments.stream()
                        .collect(groupingBy(
                                enr -> enr.getStudent().getFirstName(),
                                Collectors.averagingDouble(Enrollment::getGrade)
                        ));

        //System.out.println(averageByStudent);


        // Si on veut faire la même chose que la requête HQL:
        List<Student> result = enrollments.stream()
                .collect(groupingBy(Enrollment::getStudent)).entrySet()
                .stream()
                .filter(entry -> {
                    List<Enrollment> studentEnrollments = entry.getValue();

                    double average =
                            studentEnrollments.stream()
                                    .mapToDouble(Enrollment::getGrade)
                                    .average()
                                    .orElse(0);

                    return studentEnrollments.stream()
                            .anyMatch(e -> e.getGrade() > average);
                })
                .map(Map.Entry::getKey)
                .collect(toList());

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getFirstName());

        System.out.println(result);
    }

    public static <T> List<T> list(Query query) {
        @SuppressWarnings("unchecked")
        List<T> result = query.list();
        return result;
    }
}
