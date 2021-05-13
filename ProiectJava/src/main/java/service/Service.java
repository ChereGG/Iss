package service;

import domain.Bibliotecar;
import domain.Cititor;
import domain.Utilizator;
import repository.BibliotecarRepository;
import repository.CititorRepository;

public class Service {

    private CititorRepository cititorRepository;
    private BibliotecarRepository bibliotecarRepository;

    public Service(CititorRepository cititorRepository, BibliotecarRepository bibliotecarRepository) {
        this.cititorRepository = cititorRepository;
        this.bibliotecarRepository = bibliotecarRepository;
    }


    public void addUtilizator(Utilizator utilizator){
        if(utilizator instanceof Cititor){
            this.cititorRepository.add((Cititor) utilizator);
        }
        else if(utilizator instanceof Bibliotecar){
            this.bibliotecarRepository.add((Bibliotecar) utilizator);
        }
    }

    public Utilizator getUtilizator(String username){
        Bibliotecar b=this.bibliotecarRepository.get(username);
        Cititor c =this.cititorRepository.get(username);
        return b==null ? c : b;
    }
}
