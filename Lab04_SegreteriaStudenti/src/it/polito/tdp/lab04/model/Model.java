package it.polito.tdp.lab04.model;
import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	private List<Corso>corsi;
	CorsoDAO corsoDao=new CorsoDAO();
	
	public Model(){
		corsi = corsoDao.getTuttiICorsi();
	}
	
	public List<Corso> getCorsi(){
		return corsi;
	}



	public List<Studente> getStudentiIscritti(Corso corso){
		return getStudentiIscrittiAlCorso(corso.getCodice());
	}
}
