package SessionFactory;


import Model.Entity.Requisition;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by pyshankov on 08.10.15.
 */
public class SessionFactory {
    private static final org.hibernate.SessionFactory factory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.configure();
            configuration.addAnnotatedClass(Requisition.class);

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
   public synchronized static org.hibernate.SessionFactory getFactory(){
        if(factory!=null) return factory;
        else throw new NullPointerException();
    }
}
