package com.test;

import com.test.facts.Disease;
import com.test.facts.Medication;
import com.test.facts.Patient;
import com.test.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by akorovin on 02.12.2016.
 */
public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Patient patient1 = new Patient("Alex", 175.0, 75.5, 75);
        Patient patient2 = new Patient("Felix", 175.0, 75.5, 88);
        session.save(patient1);
        session.save(patient2);

        session.save(new Medication("Aspirin", 25.5, 3.5, patient1));
        session.save(new Disease("Disease1", "Class1", patient1));

        session.save(new Medication("Aspirin", 25.5, 3.5, patient2));
        session.save(new Disease("Disease2", "Class2", patient2));

        session.getTransaction().commit();

        Query q = session.createQuery("From Patient ");

        List<Patient> resultList = q.list();
        System.out.println("num of patients:" + resultList.size());
        for (Patient next : resultList) {
            System.out.println("next patient: " + next);
        }
    }
}
