package it.polito.tdp.lab04.controller;
import it.polito.tdp.lab04.model.*;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbxCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private Button btnCheck;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricolaInt=Integer.parseInt(txtMatricola.getText());
    	if(model.getStudente(matricolaInt)==null)
    		txtResult.setText("ERRORE: Matricola inesistente\n");
    	txtResult.setText(model.getCorsiIscritto(model.getStudente(matricolaInt)).toString());

    }
    
    @FXML
    void doCheck(ActionEvent event) {
    	int matricolaInt=Integer.parseInt(txtMatricola.getText());
    	txtCognome.setText(model.getStudente(matricolaInt).getCognome());
    	txtNome.setText(model.getStudente(matricolaInt).getNome());
    	
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	if(cmbxCorsi.getValue()==null)
    		txtResult.setText("ERRORE: Selezionare un corso\n");
    	txtResult.setText(cmbxCorsi.getValue().getStudentiIscritti().toString());
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	try {
    		if (txtMatricola.getText()==null) {
				txtResult.setText("Inserire una matricola.");
				return;
			}

			if (cmbxCorsi.getValue() == null) {
				txtResult.setText("Selezionare un corso.");
				return;
			}

			// Prendo la matricola in input
			int matricola = Integer.parseInt(txtMatricola.getText());

			// (opzionale)
			// Inserisco il Nome e Cognome dello studente nell'interfaccia
			Studente studente = model.getStudente(matricola);
			if (studente == null) {
				txtResult.setText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

			// Ottengo il nome del corso
			Corso corso = cmbxCorsi.getValue();

			// Controllo se lo studente è già iscritto al corso
			if (model.verificaIscrizione(studente, corso)) {
				txtResult.setText("Studente già iscritto a questo corso");
				return;
			}

			// Iscrivo lo studente al corso.
			// Controllo che l'inserimento vada a buon fine
			if (!model.iscriviStudenteACorso(studente, corso)) {
				txtResult.setText("Errore durante l'iscrizione al corso");
				return;
			} else {
				txtResult.setText("Studente iscritto al corso!");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	cmbxCorsi.setValue(null);
    }

    @FXML
    void initialize() {
        assert cmbxCorsi != null : "fx:id=\"cmbxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
    }


    public void setModel(Model model) {
		this.model=model;
		cmbxCorsi.getItems().addAll(model.getCorsi());
	}
}
