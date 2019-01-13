package at.htl.kursverwaltung.core;

import at.htl.kursverwaltung.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm");
        Course algebraI = new Course("Algebra I", mathe, meier, LocalDateTime.parse("01.02.2019-12:00", formatter));
        Course algebraII = new Course("Algebra II", mathe, meier, LocalDateTime.parse("05.02.2019-12:00", formatter));
        Course linMath = new Course("Linear Mathematics", mathe, bruck, LocalDateTime.parse("01.02.2019-08:00", formatter));
        Course texts = new Course("Text-Types", englisch, bruck, LocalDateTime.parse("02.02.2019-08:00", formatter));
        Course vocab = new Course("Advanced Vocabulary", englisch, bruck, LocalDateTime.parse("03.02.2019-08:00", formatter));
        Course speech = new Course("Professional Speech", deutsch, peters, LocalDateTime.parse("01.02.2019-18:00", formatter));
        Course literature = new Course("Classic Literature", deutsch, peters, LocalDateTime.parse("02.02.2019-17:00", formatter));
        Course quaternions = new Course("Complex Numbers and Quaternions", mathe, beller, LocalDateTime.parse("01.03.2019-08:00", formatter));
        
        Student kordas = new Student("Michael", "Kordas", "if150111");
        Student davis = new Student("Thomas", "Davis", "if150112");
        Student bauer = new Student("Jonas", "Bauer", "if150113");
        Student hoelder = new Student("Germund", "Hoelder", "if150114");
        Student eich = new Student("Albin", "Eich", "if150115");
        Student distel = new Student("Nicola", "Distel", "if150116");

        Subject[] arr = new Subject[]{mathe, deutsch, englisch};
        Teacher[] teachers = new Teacher[]{meier, peters, bruck, beller};
        Course[] courses = new Course[]{algebraI, algebraII, linMath, texts, vocab, speech, literature, quaternions};
        Student[] students = new Student[]{kordas, davis, bauer, hoelder, eich, distel};

        persistAll(arr, teachers, courses, students);

        persistAll(
            kordas.enrol(algebraI, algebraII),
            davis.enrol(algebraI, linMath),
            bauer.enrol(algebraII, texts),
            hoelder.enrol(texts, vocab),
            eich.enrol(speech, literature, algebraI),
            distel.enrol(quaternions, algebraII)
        );
    }


    //Object is used here to prevent heap pollution
    private void persistAll(Object[]... o){
        for (Object[] list: o) {
            for(Object item : list){
                em.persist(item);
            }
        }
    }
}
