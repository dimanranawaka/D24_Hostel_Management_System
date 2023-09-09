package lk.ijse.D24_H_M_S.util;

import lk.ijse.D24_H_M_S.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){

        sessionFactory = new Configuration().mergeProperties(Utility.getProperties()).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Employee.class).
                addAnnotatedClass(Room.class).
                addAnnotatedClass(Reservation.class).
                addAnnotatedClass(User.class)
                .buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){

        return ((factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration():factoryConfiguration);

    }
    public Session getSession (){

        return sessionFactory.openSession();

    }
}
