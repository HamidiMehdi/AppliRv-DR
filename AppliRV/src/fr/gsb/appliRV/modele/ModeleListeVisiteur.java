package fr.gsb.appliRV.modele;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import fr.gsb.appliRV.entites.Visiteur;
import fr.gsb.appliRV.technique.Session;

public class ModeleListeVisiteur extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Visiteur> visiteurs = new ArrayList<Visiteur>() ;
	
	private final String[] entetes = {"Numéro","Nom","Prénom","Choisir"} ;
	

	public ModeleListeVisiteur() {
		super();
	}
	
	@Override
	public int getRowCount() {
		return this.visiteurs.size();
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.entetes[columnIndex];
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex){
		
		case 0 :
			return this.visiteurs.get(rowIndex).getMatricule();
		
		case 1 :
			return this.visiteurs.get(rowIndex).getNom();
		
		case 2 :
			return this.visiteurs.get(rowIndex).getPrenom();
			
		case 3 :
			return "Choisir";
			
			
		default :
			return null ;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex){
		
		case 0 :
			return String.class ;
			
		case 1 :
			return String.class ;
			
		case 2 :
			return String.class ;
			
		case 3 :
			return JButton.class ;
			
		default :
			return Object.class ;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		
		if(columnIndex == 3){
			return true ;
		}
		else{
			return false ;
	     }
	}
	
	public void actualiser(){
		this.MAJvueListeVisiteurs();
		this.fireTableDataChanged();
	}
	
	public void MAJvueListeVisiteurs(){
		try{
			this.visiteurs = ((ModeleAppliRV) ModeleAppliRV.getModele()).getVisiteurs(Session.getSession().getLeDelegueRegional().getCodeRegion());
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public String getMatriculeVisiteur(int index) {
		return visiteurs.get(index).getMatricule() ;
	}
}
