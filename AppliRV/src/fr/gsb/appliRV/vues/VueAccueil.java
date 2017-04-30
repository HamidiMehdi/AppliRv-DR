package fr.gsb.appliRV.vues;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.gsb.appliRV.controleur.ControleurAccueil;
import fr.gsb.appliRV.technique.Session;

public class VueAccueil extends JFrame {

	private JMenu menuComptesRendus = new JMenu("Comptes-Rendus") ;
	private JMenu menuPraticiens = new JMenu("Praticiens") ;
	private JMenu menuFichier = new JMenu("Fichier");
	
	private JMenuItem itemCompteRenduVisiteur  = new JMenuItem("Consulter le Compte-Rendu d'un Visiteur") ;
	private JMenuItem itemTriercoefConfiance = new JMenuItem("Trier par Coefficient de Confiance");
	private JMenuItem itemTriercoefNotoriete = new JMenuItem("Trier par Coefficient de Notoriété");
	private JMenuItem itemTrierdateVisite = new JMenuItem("Trier par Date de Visite");
	private JMenuItem itemModifMdp = new JMenuItem("Modifier mon mdp");
	private JMenuItem itemDeconnexion = new JMenuItem("Deconnexion");
	private JMenuItem itemQuitter = new JMenuItem("Quitter");
	private JMenuItem itemApropos = new JMenuItem("A propos..");
	 
	private ControleurAccueil controleur;
  
    private VueListeCoefConfiance vueListeCoefConfiance= new VueListeCoefConfiance();
    private VueListeCoefNotoriete vueListeCoefNotoriete = new VueListeCoefNotoriete();
    private VueListeDateVisite vueListeDateVisite = new VueListeDateVisite();
    private VueListeVisite vueListeVisite =  new VueListeVisite();
    private JPanel panneauAccueil = new JPanel() ;
    private CardLayout clVues ; 
    private Container conteneur ; 
  
  	public VueAccueil(){
  		super();
		
		this.setTitle("Application GSB") ;
		this.setSize(1200,500) ; 
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		this.creerBarreMenus() ;
		this.setVisible(true) ;
		
		this.controleur = new ControleurAccueil(this) ;
		
		clVues = new CardLayout(5,5);
		
		conteneur = this.getContentPane();
		conteneur.setLayout(clVues);
		
		conteneur.add(panneauAccueil);
		conteneur.add(vueListeCoefNotoriete,"ListeCoefNotoriete");
		conteneur.add(vueListeDateVisite,"ListeDateVisite");
		conteneur.add(vueListeCoefConfiance,"ListeCoefConfiance");
		conteneur.add(vueListeVisite,"ListeVisite");

  	}
  		
  	private void creerBarreMenus() {

		JMenuBar barreMenus = new JMenuBar() ;

		menuFichier.add(this.itemModifMdp);
		menuFichier.add(this.itemApropos);
		menuFichier.addSeparator();
		menuFichier.add(this.itemDeconnexion);
		menuFichier.add(this.itemQuitter);
		menuComptesRendus.add(this.itemCompteRenduVisiteur) ;
		menuPraticiens.add(itemTriercoefNotoriete);
		menuPraticiens.add(itemTriercoefConfiance);
		menuPraticiens.add(itemTrierdateVisite);
		
		barreMenus.add(menuFichier);
		barreMenus.add(menuComptesRendus) ;
		barreMenus.add(menuPraticiens);

		this.setJMenuBar(barreMenus) ;
  	}
  	
  	public void changerDeVue(String vue){  		
  		this.clVues.show(this.conteneur, vue); 		
  	}

	public JMenuItem getItemCompteRenduVisiteur() {
		return itemCompteRenduVisiteur;
	}

	public JMenuItem getItemTriercoefConfiance() {
		return itemTriercoefConfiance;
	}

	public JMenuItem getItemTriercoefNotoriete() {
		return itemTriercoefNotoriete;
	}

	public JMenuItem getItemTrierdateVisite() {
		return itemTrierdateVisite;
	}

	public JMenuItem getItemApropos() {
		return itemApropos;
	}

	public JMenuItem getItemModifMdp() {
		return itemModifMdp;
	}

	public JMenuItem getItemDeconnexion() {
		return itemDeconnexion;
	}
	
	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}
	
	public VueListeVisite getVueListeVisite(){
		return this.vueListeVisite;
	}
  	
}