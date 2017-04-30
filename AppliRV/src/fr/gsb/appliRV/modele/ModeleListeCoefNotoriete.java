package fr.gsb.appliRV.modele;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import fr.gsb.appliRV.entites.Praticien;
import fr.gsb.appliRV.modele.ModeleAppliRV;
import fr.gsb.appliRV.technique.ConnexionException;

public class ModeleListeCoefNotoriete extends AbstractTableModel {
	
	private List<Praticien> praticiens ;
	private final String[] entetes = {"Numéro","Nom","Ville","Coef-Notoriete"} ;

	public ModeleListeCoefNotoriete(){
		try {
			praticiens = ModeleAppliRV.getModele().getListeCoefNotoriete();
		}
		catch (Exception e1) {
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex){
		
		case 0 :
			return new Integer(this.praticiens.get(rowIndex).getNumeroPraticien());
		
		case 1 :
			return this.praticiens.get(rowIndex).getNomPraticien();
		
		case 2 :
			return this.praticiens.get(rowIndex).getVillePraticien();

		case 3 :
			return new Float(this.praticiens.get(rowIndex).getCoefNotoriete());
			
		default :
			return null ;
		}
	}
	
	public String getColumnName(int columnIndex) {
		return this.entetes[columnIndex];
	}
	
	public void actualiser(){
		this.fireTableDataChanged();
	}
	
}
