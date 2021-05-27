package repository;

import domain.Carte;

public interface CarteRepository extends  Repository<Long, Carte>{


    Iterable<Carte> findAll();

    Carte findOneId(Long id);
}
