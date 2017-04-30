package fr.gsb.appliRV.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.gsb.appliRV.modele.ModeleAppliRV;
import fr.gsb.appliRV.technique.ConnexionException;
import fr.gsb.appliRV.technique.Session;
import fr.gsb.appliRV.vues.VueChangerMDP;

public class ControleurChangerMDP implements ActionListener{

	
	private VueChangerMDP vue ;
	
	public ControleurChangerMDP(VueChangerMDP vue) {
		// TODO Auto-generated constructor stub
		this.vue = vue ;
		this.enregistrerEcouteur();
	}
	
	
	
	public void enregistrerEcouteur(){ 
	//	this.vue.getItemCompteRenduVisiteur().addActionListener(this);
		this.vue.getbValider().addActionListener(this); 
		this.vue.getbAnnuler().addActionListener(this);

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.vue.getbValider()){
			
			String mdp = null ;
			try {
				mdp = ModeleAppliRV.getModele().getMdpUser(Session.getSession().getLeDelegueRegional().getMatricule());
			} catch (ConnexionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String mdpMis = new String(this.vue.getChampMdpActuel().getText());
			if( mdpMis != mdp ){
				System.out.println(mdp+"   "+mdpMis);
				JOptionPane.showMessageDialog(this.vue, "Le mot de passe actuel est incorrect","Attention",JOptionPane.ERROR_MESSAGE) ;

			}else{
				JOptionPane.showMessageDialog(this.vue, "Le mot de passe  est bon","Bien joué",JOptionPane.INFORMATION_MESSAGE) ;
				
				
			}
	
			
			
			
			
			
			
		}
		else if(e.getSource() == this.vue.getbAnnuler()){
			this.vue.dispose() ;
		}
	}

}
