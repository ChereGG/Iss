package repository;

import domain.Penalizare;

public interface PenalizareRepository extends Repository<Long,Penalizare> {

    Iterable<Penalizare> findPenalizariCurente(Long idCititor);
}
