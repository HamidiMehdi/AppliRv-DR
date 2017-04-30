package fr.gsb.appliRV.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.gsb.appliRV.modele.ModeleListeVisiteur;
import fr.gsb.appliRV.technique.Session;
import fr.gsb.appliRV.vues.VueAccueil;
import fr.gsb.appliRV.vues.VueChangerMDP;
import fr.gsb.appliRV.vues.vueAuthentification;

public class ControleurAccueil implements ActionListener{
	
	private VueAccueil vue ;
	

	public ControleurAccueil(VueAccueil vue) {
		// TODO Auto-generated constructor stub
		this.vue = vue;
		this.enregistrerEcouteur();
	}

	public void enregistrerEcouteur(){ 
		this.vue.getItemCompteRenduVisiteur().addActionListener(this);
		this.vue.getItemTriercoefConfiance().addActionListener(this);
		this.vue.getItemTriercoefNotoriete().addActionListener(this);
		this.vue.getItemTrierdateVisite().addActionListener(this);
		this.vue.getItemModifMdp().addActionListener(this);
		this.vue.getItemApropos().addActionListener(this);
		this.vue.getItemDeconnexion().addActionListener(this);
		this.vue.getItemQuitter().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.vue.getItemApropos()){
			JOptionPane.showMessageDialog(this.vue, "Gsb\n2016","A propos..",JOptionPane.INFORMATION_MESSAGE) ;
		}
		else if(e.getSource() == this.vue.getItemDeconnexion()){
			Session.fermer();
			this.vue.dispose();
			new vueAuthentification() ;
		}
		else if(e.getSource() == this.vue.getItemQuitter()){
			Session.fermer();
			this.vue.dispose();
		}
		else if(e.getSource() == this.vue.getItemTriercoefNotoriete()){
			this.vue.changerDeVue("ListeCoefNotoriete");
		}
		else if(e.getSource() == this.vue.getItemTriercoefConfiance()){
			this.vue.changerDeVue("ListeCoefConfiance");
		}
		else if(e.getSource() == this.vue.getItemTrierdateVisite()){
			this.vue.changerDeVue("ListeDateVisite");
		}
		else if(e.getSource() == this.vue.getItemCompteRenduVisiteur()){
			((ModeleListeVisiteur)this.vue.getVueListeVisite().getTabVisiteurs().getModel()).actualiser();
			this.vue.changerDeVue("ListeVisite");
		}
		else if(e.getSource() == this.vue.getItemModifMdp()){
			new VueChangerMDP(this.vue) ;
		}
	}
	
	

}
