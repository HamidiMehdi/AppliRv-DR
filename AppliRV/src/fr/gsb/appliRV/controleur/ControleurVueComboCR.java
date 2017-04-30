package fr.gsb.appliRV.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import fr.gsb.appliRV.modele.ModeleAppliRV;
import fr.gsb.appliRV.technique.ConnexionException;
import fr.gsb.appliRV.technique.Session;
import fr.gsb.appliRV.vues.VueComboCR;

public class ControleurVueComboCR implements ActionListener {
	
	private VueComboCR vueComboCR ;
	
	private JComboBox mois;
	private JComboBox annee;
	
	private ModeleAppliRV modele = ModeleAppliRV.getModele() ;
	

	public ControleurVueComboCR(VueComboCR vueComboCR) {
		super();
		this.vueComboCR = vueComboCR ;
		this.enregisterEcouteurs();
	 }

	private void enregisterEcouteurs() {
	
		this.vueComboCR.getbValider().addActionListener(this);
		this.vueComboCR.getbAnnuler().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 Object evenement = e.getSource() ;
		 
		 if(evenement == this.vueComboCR.getbAnnuler()){
			 
			 //On ferme la vue
			 this.vueComboCR.dispose();
		 }
		 
		 else if(evenement == this.vueComboCR.getbValider()){
		 
			 mois = vueComboCR.getCbMois();
			 int moisInt = Integer.parseInt(mois.getSelectedItem().toString());
			
			 annee = vueComboCR.getCbAnnees();
			 int anneeInt = Integer.parseInt(annee.getSelectedItem().toString());

			 //On vérifie le matricule , le mois et l'année avant la consultation du compte rendu
			 try {
				modele.getComptesRendus(modele.getMatriculeVisiteurs(moisInt, anneeInt,
						 Session.getSession().getLeDelegueRegional().getCodeRegion()),
						 moisInt, anneeInt);
			} catch (ConnexionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 String estLu ;
			 if(modele.compteRenduConsulter(modele.getNumCR()) == 0){
				 estLu = "Compte rendu n'existe pas ou jamais consulté ";
			 }else{
				 estLu = "Compte rendu deja consulter ";
			 }
			//On affiche le compte rendu connu ou inconnu selon le mois et l'année sélectionné(é)
			JOptionPane.showMessageDialog(null, "Num\u00e9ro  : " + modele.getNumCR() +"\n" 
						+ "Praticien : " + modele.getNomPraticien() + " " + modele.getPrenomPraticien() + "\n"
						+ "Ville du Praticien :  " + modele.getVillePraticien() + "\n"
						+ "Date de Visite :  " + modele.getDateVisite() + "\n"
						+ "Date de Création :  " + modele.getDateCreation() + "\n"

						//On précise que le compte rendu est consulté
						+"Consulter :  " + estLu + "\n"+"\n"
						, "Consultation du Compte Rendu", JOptionPane.INFORMATION_MESSAGE,null);
			
			//On ferme la vue
			this.vueComboCR.dispose();
	    }	
	}
	
	
	

}
