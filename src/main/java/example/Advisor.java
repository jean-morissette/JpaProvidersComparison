package example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

/**
 *
 */
@Entity
public class Advisor {

    @Id
    @GeneratedValue(generator="AdvisorSeq")
    @SequenceGenerator(name="AdvisorSeq", sequenceName="ADVISOR_SEQ", allocationSize=100)
    java.lang.Long id;

    private String advisorName;

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advisor advisor = (Advisor) o;
        return Objects.equals(id, advisor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
