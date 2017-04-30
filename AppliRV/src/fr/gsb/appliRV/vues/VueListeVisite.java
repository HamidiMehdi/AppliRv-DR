package fr.gsb.appliRV.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.gsb.appliRV.editeurs.EditeurBoutonVisiteurs;
import fr.gsb.appliRV.modele.ModeleListeCoefConfiance;
import fr.gsb.appliRV.modele.ModeleListeVisiteur;
import fr.gsb.appliRV.rendus.RenduBoutonVisiteurs;
import fr.gsb.appliRV.rendus.RenduCelluleListeVisiteurs;

public class VueListeVisite extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private ModeleListeVisiteur modeleTabVisiteurs = new ModeleListeVisiteur()  ;
	private JTable tabVisiteurs ;
	
	int mois ;
	int annee ;
	
	public VueListeVisite(){
		super() ;
		System.out.println("VueListeVisiteurs::VueListeVisiteurs()") ;
		this.creerInterfaceUtilisateur() ;
	
	}
	
	/**
	 * 
	 * @param moisInt
	 * @param anneeInt
	 */
	public VueListeVisite(int moisInt, int anneeInt) {
		this.mois = moisInt ;
		this.annee = anneeInt ;
	}

	/** Agencer les composants graphiques
	 * Créer Interface Utilisateur
	 */
	private void creerInterfaceUtilisateur(){
		System.out.println("VueListeVisiteurs::creerInterfaceUtilisateur()") ;
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		
		boxEtiquette.add(new JLabel("Liste des Visiteurs"));
		boxPrincipale.add(boxEtiquette) ;
		boxPrincipale.add(boxTableau) ;

		/** 
		 * Créer le tableau
		 **/
		this.tabVisiteurs = new JTable(this.modeleTabVisiteurs);
		
		/** 
		 * Définir la hauteur des lignes du tableau
		 **/
		this.tabVisiteurs.setRowHeight(40);
		
		/** 
		 * Créer le panneau avec ascenseur et y positionner le tableau
		 **/
		JScrollPane spVisiteurs = new JScrollPane(this.tabVisiteurs); 
		
		/** 
		 * Fixer les dimensions du panneau avec ascenseur
		 **/
		spVisiteurs.setPreferredSize(new Dimension(1090,420));
		
		/** 
		 * Ajouter le panneau avec ascenseur dans la boite d'agencement
		 **/
		boxTableau.add(spVisiteurs);
		
		this.add(boxPrincipale) ;
	
		this.appliquerRendu();
	}
	
	/** Appliquer le "rendu" au tableau
	 * 
	 */
	private void appliquerRendu(){
		this.tabVisiteurs.setDefaultRenderer(Object.class,new RenduCelluleListeVisiteurs()) ;
		this.tabVisiteurs.getColumn("Choisir").setCellRenderer(new RenduBoutonVisiteurs());
		this.tabVisiteurs.getColumn("Choisir").setCellEditor(new EditeurBoutonVisiteurs(new JCheckBox()));

	}
	
	/**
	 * @return the modeleTabVisiteurs
	 */
	public ModeleListeVisiteur getModeleTabVisiteurs() {
		return modeleTabVisiteurs;
	}

	/**
	 * @return the tabVisiteurs
	 */
	public JTable getTabVisiteurs() {
		return tabVisiteurs;
	}	
}