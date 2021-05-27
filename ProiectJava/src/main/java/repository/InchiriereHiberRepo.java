package repository;

import domain.Carte;
import domain.Inchiriere;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class InchiriereHiberRepo implements InchiriereRepository {

    private SessionFactory sessionFactory;

    private void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from agentie.persistance.hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exceptie " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Override
    public void add(Inchiriere inchiriere) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(inchiriere);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Inchiriere get(String username) {
        return null;
    }

    @Override
    public Iterable<Inchiriere> getAllNotReturned() {
        this.initialize();
        Iterable<Inchiriere> inchirieri = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Inchiriere where returned=false");
            inchirieri = (Iterable<Inchiriere>) query.getResultList();
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
        return inchirieri;
    }

    @Override
    public void returnBook(Inchiriere inchiriere) {
        this.initialize();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update Inchiriere set returned=true where id=:id");
            query.setParameter("id", inchiriere.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Iterable<Inchiriere> getAll() {
        this.initialize();
        Iterable<Inchiriere> inchirieri = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Inchiriere");
            inchirieri = (Iterable<Inchiriere>) query.getResultList();
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
        return inchirieri;
    }
}

