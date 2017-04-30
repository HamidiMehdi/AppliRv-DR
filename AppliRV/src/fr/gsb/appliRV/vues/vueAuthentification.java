package fr.gsb.appliRV.vues;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.gsb.appliRV.controleur.ControleurAuthentification;

public class vueAuthentification {
	
	private JFrame fenetre ;
	private ControleurAuthentification controleur ; // accéder au controleur
	private JButton buttonConnexion = new JButton("Connexion"); // creation du button "Connexion"
	private JTextField champMatricule = new JTextField(); // creation du champ nom de compte
	private JPasswordField champMDP = new JPasswordField(); // creation du champ mot de passe
	private JLabel matricule = new JLabel("Matricule");
	private JLabel mdp = new JLabel("Mot de passe");
	
	public vueAuthentification(){
		this.fenetre = new JFrame("Application RV"); //Création de la fenetre

		this.creerFenetre(); // appel a la methode creerFentre();
		this.controleur = new ControleurAuthentification(this);
	}
	
	public void creerFenetre(){
		
		JPanel panneau = new JPanel();
		
		panneau.setLayout(new GridLayout(3, 2, 10, 12));
		
		panneau.add(this.matricule);
		panneau.add(this.champMatricule);
		
		panneau.add(this.mdp);
		panneau.add(this.champMDP);

		panneau.add(new JLabel(""));
		panneau.add(this.buttonConnexion);
		
		fenetre.setContentPane(panneau); // on ajoute la box dans le contentPane
		
		this.afficherFenetre(); // appel a la methode afficher.Fenetre()
			
	}

	public void afficherFenetre(){
		fenetre.pack(); // adapte la taille de la fenetre en fonction du contenu
		fenetre.setLocationRelativeTo(null); //cet methode sert a mettre la fenetre directement au milieu
		fenetre.setVisible(true); // rend la fenetre visible
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'appli si on clic sur la croix
	}
	
	public JTextField getChampMatricule() {
		return champMatricule;
	}

	public JPasswordField getChampMDP() {
		return champMDP;
	}
	
	public JButton getButtonConnexion(){
		return buttonConnexion ;
	}
	
	public void fermerPage(){ // Sert a fermé la fenetre d'authentification apres authentification.
		this.fenetre.dispose();
	}
	

}
