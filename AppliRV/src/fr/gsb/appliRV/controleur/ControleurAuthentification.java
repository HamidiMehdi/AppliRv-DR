package fr.gsb.appliRV.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.gsb.appliRV.entites.DelegueRegional;
import fr.gsb.appliRV.entites.Visiteur;
import fr.gsb.appliRV.modele.ModeleAppliRV;
import fr.gsb.appliRV.technique.ConnexionException;
import fr.gsb.appliRV.technique.Session;
import fr.gsb.appliRV.vues.VueAccueil;
import fr.gsb.appliRV.vues.vueAuthentification;

public class ControleurAuthentification implements ActionListener{
	private vueAuthentification vue ;

	public ControleurAuthentification(vueAuthentification vueAuthentification) {
		this.vue = vueAuthentification ; // liéer la vue au controleur
		this.enregistrerEcouteur();
	}
	
	public void enregistrerEcouteur(){ // 
		this.vue.getButtonConnexion().addActionListener(this);  // on se met a l'écoute du button Connexion
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object sourceEvenement = e.getSource() ;
		
		if( sourceEvenement == this.vue.getButtonConnexion()){  // si la source est le button Connexion...
			
			String matricule = this.vue.getChampMatricule().getText();
			String mdp = new String (this.vue.getChampMDP().getText());
			DelegueRegional delegueRegional = null;
	
			try {
			delegueRegional = ModeleAppliRV.getModele().seConnecter(matricule, mdp);
			} 
			catch (SQLException | ClassNotFoundException | ConnexionException e1) {
				e1.printStackTrace();
			}
			
			if(delegueRegional != null){
				this.vue.fermerPage();
				new VueAccueil();
				JOptionPane.showMessageDialog(null , "Vous venez de vous authentifier", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
				Session.ouvrir(delegueRegional);
				System.out.println(Session.getSession().getLeDelegueRegional().getCodeRegion());
			}
			else{
				JOptionPane.showMessageDialog(null , "Délégué non identifier", "Connexion refusé", JOptionPane.ERROR_MESSAGE);
			}
			
	    }
		
	}	

}

