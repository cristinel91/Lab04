package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Studente {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String CDS;
	
	private List<Corso> corsiIscritto=new LinkedList<Corso>();
	
	public Studente(int matricola, String cognome, String nome, String cDS) {
		
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.CDS = cDS;
	}

	public String toString() {
		return matricola+" "+cognome+" "+nome+"\n";
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}
	

	public int getMatricola() {
		return matricola;
	}
}
