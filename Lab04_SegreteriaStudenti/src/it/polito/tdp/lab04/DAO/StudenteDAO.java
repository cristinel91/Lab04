package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudente(int matricola) {

		final String sql = "SELECT * "+
						   "FROM studente "+
						   "WHERE matricola=?";
		Studente stemp=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);

			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				stemp=new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
			}

			return stemp;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> getCorsiIscritto(Studente studente){
		List<Corso> corsi = new LinkedList<Corso>();
		
		final String sql = "SELECT * "+
				 		   "FROM corso "+
				 		   "WHERE codins in (SELECT codins "+
									  		"FROM iscrizione "+
									  		"WHERE matricola in(SELECT matricola "+
									  							"FROM studente "+
									  							"WHERE matricola=?))";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,studente.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				corsi.add(new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd")));
			}
			return corsi;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}

