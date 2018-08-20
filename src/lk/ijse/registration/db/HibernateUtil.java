package lk.ijse.registration.db;

import lk.ijse.registration.entity.Course;
import lk.ijse.registration.entity.Register;
import lk.ijse.registration.entity.Register_PK;
import lk.ijse.registration.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File hibernateProp = new File("resource/application.properties");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProp)
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Register.class)
//                .addAnnotatedClass(Register_PK.class)
                .addAnnotatedClass(Student.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
