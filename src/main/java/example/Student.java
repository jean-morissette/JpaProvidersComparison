package example;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(generator="StudentSeq")
    @SequenceGenerator(name="StudentSeq", sequenceName="STUDENT_SEQ", allocationSize=100)
    java.lang.Long id;

    private String studentName;

    @ManyToOne(cascade = {})
    @JoinColumn(name="ADVISOR_ID", nullable=true)
    Advisor advisor;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
