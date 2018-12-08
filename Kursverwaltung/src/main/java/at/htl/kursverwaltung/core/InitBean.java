package at.htl.kursverwaltung.core;

import at.htl.kursverwaltung.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

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
        Course algebraII = new Course("Algebra II", mathe, meier);
        Course linMath = new Course("Linear Mathematics", mathe, bruck);
        Course texts = new Course("Text-Types", englisch, bruck);
        Course vocab = new Course("Advanced Vocabulary", englisch, bruck);
        Course speech = new Course("Professional Speech", deutsch, peters);
        Course literature = new Course("Classic Literature", deutsch, peters);
        Course quaternions = new Course("Complex Numbers and Quaternions", mathe, beller);
        
        Student kordas = new Student("Michael", "Kordas", "if150111");
        Student davis = new Student("Thomas", "Davis", "if150112");
        Student bauer = new Student("Jonas", "Bauer", "if150113");
        Student hoelder = new Student("Germund", "Hoelder", "if150114");
        Student eich = new Student("Albin", "Eich", "if150115");
        Student distel = new Student("Nicola", "Distel", "if150116");

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

        enrol(algebraI, kordas);
        enrol(algebraII, kordas);
        enrol(algebraI, davis);
        enrol(linMath, davis);
        enrol(algebraII, bauer);
        enrol(texts, bauer);
        enrol(texts, hoelder);
        enrol(vocab, hoelder);
        enrol(speech, eich);
        enrol(literature, eich);
        enrol(algebraI, eich);
        enrol(quaternions, distel);
        enrol(algebraII, distel);
    }

    private void enrol(Course c, Student s){
        em.persist(new Enrolment(c, s, LocalDateTime.now()));
    }
}
