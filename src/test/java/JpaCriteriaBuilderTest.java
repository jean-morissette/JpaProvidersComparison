import example.Student;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 *
 */
public class JpaCriteriaBuilderTest {

    @Test
    public void testHibernateLeftOuterJoin() {
        performLeftOuterJoin("hibernate-test");
    }

    @Test
    public void testEclipseLinkLeftOuterJoin() {
        performLeftOuterJoin("eclipselink-test");
    }

    private void performLeftOuterJoin(String persistenceUnit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager em = emf.createEntityManager();

        // persist entities
        em.getTransaction().begin();
        Student s = new Student();
        s.setStudentName("Foo");
        em.persist(s);
        em.getTransaction().commit();

        // retrieve entities without order by clause
        List<Student> list = performLeftOuterJoin(em, false);

        // retrieve entities with order by clause
        List<Student> list2 = performLeftOuterJoin(em, true);

        // the lists should contain exactly the same entities
        Assert.assertTrue(list.containsAll(list2));
        Assert.assertTrue(list.size() == list2.size());
    }

    private static <T> List<T> performLeftOuterJoin(EntityManager em, boolean orderByAdvisorName) {

        Class entityClass = Student.class;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(entityClass);
        Root<T> from = criteria.from(entityClass);

        Join join = from.join("advisor", JoinType.LEFT);

        criteria.select(from);

        if (orderByAdvisorName) {
            criteria.orderBy(cb.desc(from.get("advisor").get("advisorName")));
        }

        TypedQuery<T> q = em.createQuery(criteria);

        // get result
        List<T> results = q.getResultList();

        return results;
    }

}
