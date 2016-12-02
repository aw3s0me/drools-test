package com.test;

import com.test.facts.Disease;
import com.test.facts.Medication;
import com.test.facts.Patient;
import com.test.utilities.HibernateUtil;
import com.test.utilities.RuleRunner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;

import java.util.List;

/**
 * Created by akorovin on 02.12.2016.
 */
public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
        config.setOption( EventProcessingOption.CLOUD );

        session.beginTransaction();

        Patient patient1 = new Patient("Alex", 175.0, 75.5, 75);
        Patient patient2 = new Patient("Felix", 175.0, 75.5, 88);

        patient1.addDisease(new Disease("Disease1", "Class1"));
        patient1.addDisease(new Disease("Disease2", "Class2"));

        patient1.addMedication(new Medication("verapamil", 25.5, 3.5));
        patient1.addMedication(new Medication("Beta-blocker", 25.5, 3.5));

        session.save(patient1);
        session.save(patient2);

        session.save(new Medication("Beta-blocker", 25.5, 3.5, patient1));
        session.save(new Medication("verapamil", 25.5, 3.5, patient1));
        session.save(new Disease("Disease1", "Class1", patient1));

        session.save(new Medication("Aspirin", 25.5, 3.5, patient2));
        session.save(new Disease("Disease2", "Class2", patient2));

        session.getTransaction().commit();

        // Query q = session.createQuery("select p from Patient, Medication as p JOIN Medication as m ON p.medication_id = m.id");

        // Query q = session.createQuery("from Patient as patient left join patient.medications as medications left join patient.diseases as diseases");

        // Query q = session.createSQLQuery("select {p.*}, {m.*} from patient as patient inner join medication on patient.id=m.patient_id");
        Query q2 = session.createQuery("from Patient as patient fetch all properties");

        List<Patient> resultList = q2.getResultList();
//        System.out.println("num of patients:" + resultList.size());

        for (Patient next : resultList) {
            next.getMedications();
            System.out.println("next patient: " + next);
            new RuleRunner().runRule( "rules/stopp.drl", next );
        }
    }
}
