package fr.gsb.appliRV.rendus;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenduBoutonVisiteurs  extends JButton implements TableCellRenderer{

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		System.out.println("RenduBoutonVisiteurs::getTableCellRendererComponent()");
		
		//Savoir si la valeur du bouton est différente de  null
		if(value != null){
			this.setText(value.toString()) ;
		}
		
		else {
			this.setText("") ;
		}
		
		return this;
	}
}
