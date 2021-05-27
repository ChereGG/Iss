package service;

import domain.*;
import repository.*;

public class Service {

    private CititorRepository cititorRepository;
    private BibliotecarRepository bibliotecarRepository;
    private CarteRepository carteRepository;
    private InchiriereRepository inchiriereRepository;
    private PenalizareRepository penalizareRepository;
    public Service(CititorRepository cititorRepository, BibliotecarRepository bibliotecarRepository,CarteRepository carteRepository,InchiriereRepository inchiriereRepository,PenalizareRepository penalizareRepository) {
        this.cititorRepository = cititorRepository;
        this.bibliotecarRepository = bibliotecarRepository;
        this.carteRepository =carteRepository;
        this.inchiriereRepository=inchiriereRepository;
        this.penalizareRepository=penalizareRepository;
    }

    public Iterable<Carte> findAllCarti(){
        return this.carteRepository.findAll();
    }

    public Iterable<Inchiriere> findAllInchirieriNotReturned(){
        return  this.inchiriereRepository.getAllNotReturned();
    }
    public void addUtilizator(Utilizator utilizator){
        if(utilizator instanceof Cititor){
            this.cititorRepository.add((Cititor) utilizator);
        }
        else if(utilizator instanceof Bibliotecar){
            this.bibliotecarRepository.add((Bibliotecar) utilizator);
        }
    }

    public void returnBook(Inchiriere inchiriere){
        this.inchiriereRepository.returnBook(inchiriere);
    }
    public Carte findCarteById(Long id){
        return this.carteRepository.findOneId(id);
    }

    public void addInchiriere(Inchiriere inchiriere){
        this.inchiriereRepository.add(inchiriere);
    }

    public Cititor findCititorById(Long id){
        return this.cititorRepository.getById(id);
    }
    public Utilizator findUtilizator(String username){
        Bibliotecar b=this.bibliotecarRepository.get(username);
        Cititor c =this.cititorRepository.get(username);
        return b==null ? c : b;
    }

    public void addPenalizare(Penalizare penalizare){
        this.penalizareRepository.add(penalizare);
    }
    public Iterable<Penalizare> findAllPenalizareForCititor(Long id){
        return this.penalizareRepository.findPenalizariCurente(id);
    }
    public Iterable<Inchiriere> findAllInchirieri(){
        return this.inchiriereRepository.getAll();
    }
}
