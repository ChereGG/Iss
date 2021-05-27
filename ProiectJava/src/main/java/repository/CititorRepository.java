package repository;

import domain.Cititor;

public interface CititorRepository extends Repository<Long, Cititor> {

    Cititor getById(Long id);
}
