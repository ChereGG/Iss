package repository;

import domain.Bibliotecar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class BibliotecarHiberRepo implements  BibliotecarRepository{
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
    public void add(Bibliotecar bibliotecar) {
        this.initialize();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bibliotecar);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Bibliotecar get(String username) {
        this.initialize();
        Bibliotecar bibliotecar =null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query=session.createQuery("from Bibliotecar where username=:username");
            query.setParameter("username",username);
            bibliotecar =(Bibliotecar) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        }
        catch (Exception ex) {
            this.close();
        }
        return bibliotecar;
    }

}
