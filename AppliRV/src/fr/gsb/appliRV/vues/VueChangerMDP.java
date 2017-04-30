package fr.gsb.appliRV.vues;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import fr.gsb.appliRV.controleur.ControleurChangerMDP;


public class VueChangerMDP extends JDialog{


	VueAccueil vueParente ;
	private JLabel labelMdpActuel = new JLabel("Mot de passe actuel");
	private JLabel labelMdp1 = new JLabel("Nouveau mot de passe");
	private JLabel labelMdp2 = new JLabel("Ressaisir le nouveau mot de passe");
	private JPasswordField champMdpActuel = new JPasswordField() ;
	private JPasswordField champNewMDP1 = new JPasswordField() ;
	private JPasswordField champNewMDP2 = new JPasswordField() ;
	private JButton bValider = new JButton("Valider");
	private JButton bAnnuler = new JButton("Annuler");

	private ControleurChangerMDP controleur ;
	
	private JPanel panneau = new JPanel();
	
	
	public VueChangerMDP(VueAccueil vueParente){
		// Appel du constructeur de la super-classe
		//	Troisième argument : true pour indiquer que la boîte de dialogue est modale 
		super(vueParente,"Changer votre mot de passe",true) ;
		
		// Mémorise la vue parente qui est la fenêtre principale de l'application
		this.vueParente = vueParente ;
		
		// Crée le formulaire de saisie
		this.creerInterface() ;
		
		this.pack() ;
		this.setLocationRelativeTo(null) ;
		this.setResizable(false);
		this.controleur = new ControleurChangerMDP(this) ;
		
		// Affiche la boîte de dialogue
		this.setVisible(true) ;
	}

	
	
	public void creerInterface(){
				
		panneau.setLayout(new GridLayout(4, 2, 10, 12));
		
		panneau.add(this.labelMdpActuel);
		panneau.add(this.champMdpActuel);
		
		panneau.add(this.labelMdp1);
		panneau.add(this.champNewMDP1);

		panneau.add(this.labelMdp2);
		panneau.add(this.champNewMDP2);
		
		panneau.add(this.bValider);
		panneau.add(this.bAnnuler);
		
		this.setContentPane(panneau); // on ajoute la box dans le contentPane
		
	}



	public JTextField getChampMdpActuel() {
		return champMdpActuel;
	}



	public JTextField getChampNewMDP1() {
		return champNewMDP1;
	}



	public JTextField getChampNewMDP2() {
		return champNewMDP2;
	}



	public JButton getbValider() {
		return bValider;
	}



	public JButton getbAnnuler() {
		return bAnnuler;
	}
	
	
	
	
	
}
