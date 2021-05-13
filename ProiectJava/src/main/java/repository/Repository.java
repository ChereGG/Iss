package repository;

import domain.Entity;

public interface Repository <ID, E extends Entity<ID>> {

    void add(E e);
    E get(String username);
}
