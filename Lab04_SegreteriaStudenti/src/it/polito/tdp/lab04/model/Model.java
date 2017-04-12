package it.polito.tdp.lab04.model;
import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private List<Corso>corsi;
	CorsoDAO corsoDao=new CorsoDAO();
	private List<Studente>studenti;
	StudenteDAO studenteDao=new StudenteDAO();
	private List<Corso> corsiIscritto=new LinkedList<Corso>();
	
	public Model(){
		corsi = corsoDao.getTuttiICorsi();
	}
	
	public List<Corso> getCorsi(){
		return corsi;
	}
	
	public List<Studente> getStudenti(){
		return studenti;
	}
	
	public List<Corso> getCorsiIscritto(Studente studente){
		StudenteDAO studenteDao=new StudenteDAO();
		corsiIscritto.addAll(studenteDao.getCorsiIscritto(studente));
		return corsiIscritto;
	}
	
	public Studente getStudente(int matricola){
		StudenteDAO stempDao=new StudenteDAO();
		return stempDao.getStudente(matricola);
	}
	
	public boolean verificaIscrizione(Studente studente, Corso corso){
		CorsoDAO ctempDao=new CorsoDAO();
		if(ctempDao.getStudentiIscrittiAlCorso(corso).contains(studente))
			return true;
		return false;
	}
}
