package at.htl.kursverwaltung.core;

import at.htl.kursverwaltung.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("Hei");
        Subject mathe = new Subject("Mathe");
        Subject deutsch = new Subject("Deutsch");
        Subject englisch = new Subject("Englisch");
        Teacher meier = new Teacher("Heinrich", "Meier", "HM123");
        Teacher peters = new Teacher("Dieter", "Peters", "DP321");
        Teacher bruck = new Teacher("Peter", "Bruck", "PB2312");
        Teacher beller = new Teacher("Renate", "Beller", "RB312");
        Course algebraI = new Course("Algebra I", mathe, meier);
        meier.getCourseList().add(algebraI);
        Course algebraII = new Course("Algebra II", mathe, meier);
        meier.getCourseList().add(algebraII);
        Course linMath = new Course("Linear Mathematics", mathe, bruck);
        bruck.getCourseList().add(linMath);
        Course texts = new Course("Text-Types", englisch, bruck);
        bruck.getCourseList().add(texts);
        Course vocab = new Course("Advanced Vocabulary", englisch, bruck);
        bruck.getCourseList().add(vocab);
        Course speech = new Course("Professional Speech", deutsch, peters);
        peters.getCourseList().add(speech);
        Course literature = new Course("Classic Literature", deutsch, peters);
        peters.getCourseList().add(literature);
        Course quaternions = new Course("Complex Numbers and Quaternions", mathe, beller);
        beller.getCourseList().add(quaternions);

        Student kordas = new Student("Michael", "Kordas", "if150111");
        Student davis = new Student("Thomas", "Davis", "if150112");
        Student bauer = new Student("Jonas", "Bauer", "if150113");
        Student hoelder = new Student("Germund", "Hoelder", "if150114");
        Student eich = new Student("Albin", "Eich", "if150115");
        Student distel = new Student("Nicola", "Distel", "if150116");

        Enrolment e1 = kordas.enrol(algebraI);
        Enrolment e2 = kordas.enrol(algebraII);
        Enrolment e3 = davis.enrol(algebraI);
        Enrolment e4 = davis.enrol(linMath);
        Enrolment e5 = bauer.enrol(algebraII);
        Enrolment e6 = bauer.enrol(texts);
        Enrolment e7 = hoelder.enrol(texts);
        Enrolment e8 = hoelder.enrol(vocab);
        Enrolment e9 = eich.enrol(speech);
        Enrolment e10 = eich.enrol(literature);
        Enrolment e11 = eich.enrol(algebraI);
        Enrolment e12 = distel.enrol(quaternions);
        Enrolment e13 = distel.enrol(algebraII);

        em.persist(mathe);
        em.persist(deutsch);
        em.persist(englisch);
        em.persist(meier);
        em.persist(peters);
        em.persist(bruck);
        em.persist(beller);
        em.persist(algebraI);
        em.persist(algebraII);
        em.persist(linMath);
        em.persist(texts);
        em.persist(vocab);
        em.persist(speech);
        em.persist(literature);
        em.persist(quaternions);
        em.persist(kordas);
        em.persist(davis);
        em.persist(bauer);
        em.persist(hoelder);
        em.persist(eich);
        em.persist(distel);
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);
        em.persist(e5);
        em.persist(e6);
        em.persist(e7);
        em.persist(e8);
        em.persist(e9);
        em.persist(e10);
        em.persist(e11);
        em.persist(e12);
        em.persist(e13);
    }
}
