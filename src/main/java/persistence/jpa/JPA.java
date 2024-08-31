package persistence.jpa;


import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPA {

    public static void main(String[] args) {
        try (var factory = Persistence.createEntityManagerFactory("edu-mysql"); // should match with persistence.xml
             var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();

            Employee employee = new Employee(1, "name1");
            entityManager.persist(employee);

            entityManager.getTransaction().commit();
        }
    }
}
