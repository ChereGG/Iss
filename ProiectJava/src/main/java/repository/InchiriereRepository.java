package repository;

import domain.Inchiriere;

public interface InchiriereRepository extends  Repository<Long, Inchiriere> {

    Iterable<Inchiriere>getAllNotReturned();

    void returnBook(Inchiriere inchiriere);

    Iterable<Inchiriere>getAll();
}
