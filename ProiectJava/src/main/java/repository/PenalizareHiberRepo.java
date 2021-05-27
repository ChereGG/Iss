package repository;

import domain.Inchiriere;
import domain.Penalizare;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PenalizareHiberRepo implements PenalizareRepository{
    private SessionFactory sessionFactory;

    private void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
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
    public void add(Penalizare penalizare) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(penalizare);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Penalizare get(String username) {
        return null;
    }

    @Override
    public Iterable<Penalizare> findPenalizariCurente(Long idCititor) {
        this.initialize();
        Iterable<Penalizare> penalizari = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Penalizare where idUtilizator=:id and ended=false");
            query.setParameter("id", idCititor);
            penalizari = (Iterable<Penalizare>) query.getResultList();
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
        return penalizari;
    }
}
