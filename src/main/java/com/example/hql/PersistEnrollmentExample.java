package com.example.hql;

import com.example.hql.entity.Student;
import com.example.hql.entity.Course;
import com.example.hql.entity.Enrollment;
import com.example.hql.util.HibernateTestUtil;
import org.hibernate.Session;

public class PersistEnrollmentExample {

    public static void main(String[] args) {

        Session session = HibernateTestUtil.getSession();
        session.beginTransaction();

        // 1️⃣ Récupérer Georges
        Student georges = (Student) session
                .createQuery("FROM Student s WHERE s.firstName = :name")
                .setParameter("name", "Georges")
                .uniqueResult();

        if (georges == null) {
            System.out.println("Étudiant Georges introuvable !");
            session.getTransaction().rollback();
            session.close();
            return;
        }

        // 2️⃣ Récupérer ou créer un cours
        Course mathCourse = (Course) session.createQuery("FROM Course c WHERE c.title = :title")
                .setParameter("title", "Mathematics")
                .uniqueResult();

        if (mathCourse == null) {
            mathCourse = new Course("Mathematics", 5);
            session.save(mathCourse);
        }

        // 3️⃣ Créer l'enrollment
        Enrollment enrollment = new Enrollment(georges, mathCourse, 18.0);

        // 4️⃣ Persister
        session.save(enrollment);

        // Optionnel : mettre à jour les collections bidirectionnelles
        georges.getEnrollments().add(enrollment);
        mathCourse.getEnrollments().add(enrollment);

        session.getTransaction().commit();
        session.close();

        System.out.println("Enrollment ajouté pour Georges !");
    }
}
