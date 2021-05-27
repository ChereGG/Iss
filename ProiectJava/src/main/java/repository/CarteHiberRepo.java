package repository;

import domain.Carte;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CarteHiberRepo implements  CarteRepository {

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
    public void add(Carte carte) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(carte);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    @Override
    public Carte get(String username) {
        return null;
    }

    @Override
    public Iterable<Carte> findAll() {
        this.initialize();
        Iterable<Carte> carti = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Carte");
            carti = (ArrayList<Carte>) query.getResultList();
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
        return carti;
    }

    @Override
    public Carte findOneId(Long id) {
        this.initialize();
        Carte carte = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Carte where id=:id");
            query.setParameter("id", id);
            carte = (Carte) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
        return carte;
    }
}
