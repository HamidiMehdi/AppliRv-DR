package fr.gsb.appliRV.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import fr.gsb.appliRV.modele.ModeleListeVisiteur;
import fr.gsb.appliRV.vues.VueAccueil;
import fr.gsb.appliRV.vues.VueComboCR;

public class ControleurBoutonVisiteurs implements ActionListener {
	
	private int row ;
	private int column ;
	private JTable table ;
	
	private VueAccueil vue ;
	private VueComboCR vueComBoCR ;
	
	public ControleurBoutonVisiteurs() {
		super();
		System.out.println("ControleurBoutonVisiteurs::ControleurBoutonVisiteurs");
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}


	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	

	/**
	 * @return the vueComBoCR
	 */
	public VueComboCR getVueComBoCR() {
		return vueComBoCR;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ControleurBoutonVisiteurs::actionPerformed()");
	
		String matriculeVisiteur =  ((ModeleListeVisiteur) this.table.getModel()).getMatriculeVisiteur(this.row);
		
				switch(this.column){
		
				case 3 :
					vueComBoCR = new VueComboCR(this.vue);
						break ;
			}		
	 }
}
