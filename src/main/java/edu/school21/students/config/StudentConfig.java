package edu.school21.students.config;

import com.github.javafaker.Faker;
import edu.school21.students.entity.*;
import edu.school21.students.repository.DisciplineRepository;
import edu.school21.students.repository.GradeRepository;
import edu.school21.students.repository.GroupRepository;
import edu.school21.students.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository,
                                         DisciplineRepository disciplineRepository,
                                         GradeRepository gradeRepository,
                                         GroupRepository groupRepository) {
        return args -> {
            List<String> names = new ArrayList<>(Arrays.asList("Bookworms", "Data Diggers",
                    "Science Squad", "Tech Titans", "Math Magicians",
                    "Writing Warriors", "Art Enthusiasts", "Language Lovers"));
            List<Group> groups = new ArrayList<>();
            for (String n : names) {
                groups.add(new Group(n));
            }
            groupRepository.saveAll(groups);


            Faker faker = new Faker();
            List<Student> students = new ArrayList<>();
            Date from = new GregorianCalendar(1994, Calendar.JANUARY, 1).getTime();
            Date to = new GregorianCalendar(2005, Calendar.JANUARY, 1).getTime();

            for (int i = 0; i < 3; i++) {
                LocalDate localDate = faker.date().between(from, to).toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                students.add(new Student(faker.name().firstName(),
                        faker.name().lastName(), localDate,
                        groups.get(i % groups.size())));
            }
            studentRepository.saveAll(students);

            List<Discipline> disciplines = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                disciplines.add(new Discipline(faker.educator().course()));
            }
            disciplines = disciplines.stream().distinct()
                    .collect(Collectors.toList());
            disciplineRepository.saveAll(disciplines);

            List<Grade> grades = new ArrayList<>();
            int i = 0;
            for (Student st: students) {
                grades.add(new Grade(Mark.ACCEPTABLE,
                        "good",
                        faker.date().past(365, TimeUnit.DAYS)
                                .toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        st, disciplines.get(i % disciplines.size())));
                i++;
            }
            gradeRepository.saveAll(grades);
        };
    }
}
