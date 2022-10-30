package camelinaction;

import camelinaction.pojo.Student;
import camelinaction.pojo.Teacher;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConverter;

import java.util.concurrent.ThreadLocalRandom;


@Converter
public final class StudentConverter {

    @Converter
    public static Teacher toTeacher(Student pStudent, Exchange exchange) {

        TypeConverter converter = exchange.getContext().getTypeConverter();
        Student student = converter.convertTo(Student.class, pStudent);
        if (student.getStudentName() == null) {
            throw new IllegalArgumentException("data is invalid");
        }

        Teacher teacher = new Teacher();
        teacher.setTeacherName("Mr. " + student.getStudentName());
        teacher.setTeacherId(1000 + student.getStudnetId());
        teacher.setSalary(ThreadLocalRandom.current().nextInt(100, 500));

        return teacher;
        //return new Teacher("Vasya", 2, 100);
    }

}
