package fr.gsb.appliRV.modele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import fr.gsb.appliRV.entites.Praticien;

public class ModeleListeDateVisite extends AbstractTableModel {
	
	private List<Praticien> praticiens ;
	private final String[] entetes = {"Numéro","Nom","Ville","Date de Visite"} ;
	
	public ModeleListeDateVisite() {
		super();
		try {
			praticiens = ModeleAppliRV.getModele().getListeDateVisite() ;
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public int getRowCount() {
		return this.praticiens.size();
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
			return new Integer(this.praticiens.get(rowIndex).getNumeroPraticien());
		
		case 1 :
			return this.praticiens.get(rowIndex).getNomPraticien();
		
		case 2 :
			return this.praticiens.get(rowIndex).getVillePraticien();
		
		case 3 :
			return this.praticiens.get(rowIndex).getDateVisite();

		default :
			return null ;
		}
	}

	public void actualiser(){
		this.fireTableDataChanged();
	}
}