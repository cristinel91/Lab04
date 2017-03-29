package it.polito.tdp.lab04.model;
import java.util.*;

public class Corso {
	
	private String codins;
	private String nome;
	private int crediti;
	private int pd;
	
	private List<Studente> studentiIscritti=new LinkedList<Studente>();
	
	public Corso(String codins, int crediti, String nome, int pd) {
		this.codins = codins;
		this.nome = nome;
		this.crediti = crediti;
		this.pd = pd;
	}

	public String toString() {
		return codins+" "+nome;
	}
	
	public String getCodice(){
		return codins;
	}

	public String getStudentiIscritti() {
		return null;
	}
}
