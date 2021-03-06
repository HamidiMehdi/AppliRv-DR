package fr.gsb.appliRV.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.gsb.appliRV.modele.ModeleListeCoefConfiance;

public class VueListeCoefConfiance extends JPanel{
	
	private ModeleListeCoefConfiance modeleTabPraticiens = new ModeleListeCoefConfiance()  ;
	private JTable tabPraticiens ;
	
	public VueListeCoefConfiance(){
		super() ;
		this.creerInterfaceUtilisateur() ;
	}

	private void creerInterfaceUtilisateur(){
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		
		boxEtiquette.add(new JLabel("Coefficient confiance - croissant"));
		boxPrincipale.add(boxEtiquette) ;
		boxPrincipale.add(boxTableau) ;

		this.tabPraticiens = new JTable(this.modeleTabPraticiens);
		this.tabPraticiens.setRowHeight(50);

		JScrollPane spPraticiens = new JScrollPane(this.tabPraticiens); 
		spPraticiens.setPreferredSize(new Dimension(1090,420));
		
		boxTableau.add(spPraticiens);
		
		this.add(boxPrincipale) ;
		this.MAJtable();
	}

	private void MAJtable(){
		modeleTabPraticiens.actualiser();
	}
}