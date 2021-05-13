package repository;

import domain.Cititor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class CititorHiberRepo implements CititorRepository{

    private SessionFactory sessionFactory;

    private void initialize(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    private void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void add(Cititor cititor) {
        this.initialize();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(cititor);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Cititor get(String username) {
        this.initialize();
        Cititor cititor=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query=session.createQuery("from Cititor where username=:username");
            query.setParameter("username",username);
            cititor=(Cititor) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        }
        catch (Exception ex) {
            this.close();
        }
        return cititor;
    }
}
