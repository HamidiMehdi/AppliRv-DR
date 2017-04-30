package fr.gsb.appliRV.modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import fr.gsb.appliRV.entites.CompteRendu;
import fr.gsb.appliRV.entites.DelegueRegional;
import fr.gsb.appliRV.entites.Praticien;
import fr.gsb.appliRV.entites.Visiteur;
import fr.gsb.appliRV.technique.ConnexionBD;
import fr.gsb.appliRV.technique.ConnexionException;
import fr.gsb.appliRV.technique.Session;

public class ModeleAppliRV {

private static ModeleAppliRV modele = null ;
	

private Integer numCR = -1; 
private String nomPraticien = (String)null;
private String prenomPraticien = (String)null;
private String villePraticien = (String)null;
private Date dateVisite = (Date)null;
private Date dateCreation = (Date)null;
private String situation = (String)null;

	private ModeleAppliRV() {
		super(); 
	}

	
	public static ModeleAppliRV getModele() {
		if (modele == null) {
			modele = new ModeleAppliRV() ;
		}
		return modele;
	}
	 public DelegueRegional seConnecter(String matricule, String mdp) throws ClassNotFoundException, SQLException, ConnexionException {

			Connection connexion = ConnexionBD.getConnexion();
			String requete = "SELECT TRAVAILLER.REG_CODE FROM VISITEUR "
					+ "INNER JOIN TRAVAILLER ON VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE "
					+ "WHERE TRAVAILLER.JJMMAA = ( SELECT MAX(JJMMAA)  "
					+ "FROM TRAVAILLER AS T2 WHERE TRAVAILLER.VIS_MATRICULE = T2.VIS_MATRICULE) "
					+ "AND TRA_ROLE = 'Delegue' "
					+ "AND VISITEUR.VIS_MATRICULE= ? " 
					+ "AND VISITEUR.VIS_MDP= ? ;";

			
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
			pstmt.setString(1,matricule);
			pstmt.setString(2,mdp);
			
			ResultSet resultat = pstmt.executeQuery();
			
			if(resultat.next()){
				 String codeRegion = resultat.getString("REG_CODE");
				 DelegueRegional delegueRegional = new DelegueRegional(matricule,codeRegion);
				 Session.ouvrir(delegueRegional);
				 pstmt.close();
				 return delegueRegional ;
			}
			else {
				pstmt.close();
				return null ;
			}
	    }
	 
	
	 public String getMdpUser(String matricule) throws ConnexionException, SQLException{
		 
		
			Connection connexion = ConnexionBD.getConnexion();
		 	String requete = "SELECT VIS_MDP FROM VISITEUR WHERE VIS_MATRICULE = ?";
			
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
			pstmt.setString(1,matricule);
			ResultSet resultat = pstmt.executeQuery();
			if(resultat.next()){
				String mdp = resultat.getString("VIS_MDP") ;
				pstmt.close();
				return mdp ;
			}else{
				 pstmt.close();
				 return null ;
			}
	 }
	 
	 
	 
	public List<Praticien> getListeCoefNotoriete() throws Exception{
		
		List<Praticien> praticiens = new ArrayList<Praticien>() ;
		
		Connection connexion = ConnexionBD.getConnexion();
		Statement stmt;
		try {
			stmt = (Statement) connexion.createStatement();
	
			String requete = "SELECT R1.PRA_NUM,PRA_NOM,PRA_VILLE,RAP_COEFCONFIANCE,RAP_DATE_VISITE,PRA_COEFNOTORIETE "
					+ " FROM RAPPORT_VISITE AS R1 INNER JOIN PRATICIEN "
					+ " ON R1.PRA_NUM = PRATICIEN.PRA_NUM "
					+ "WHERE RAP_DATE_VISITE = "
						+ "(SELECT MAX(RAP_DATE_VISITE) "
						+ "FROM RAPPORT_VISITE AS R2 "
						+ "WHERE R1.PRA_NUM = R2.PRA_NUM  "
						+ "GROUP BY R2.PRA_NUM ) "
					+ "AND RAP_COEFCONFIANCE <=3 "
					+ "ORDER BY PRA_COEFNOTORIETE DESC;";
		
			ResultSet resultat = stmt.executeQuery(requete);
			
			while(resultat.next()){

				Integer numPraticien = resultat.getInt("R1.PRA_NUM");
				String nomPraticien = resultat.getString("PRA_NOM");
				String villePraticien = resultat.getString("PRA_VILLE");
				Integer coefConfiance = resultat.getInt("RAP_COEFCONFIANCE");
				Date dateVisite = resultat.getDate("RAP_DATE_VISITE");
				Float coefNotoriete = resultat.getFloat("PRA_COEFNOTORIETE");
				
				praticiens.add(new Praticien(numPraticien, nomPraticien,villePraticien,coefConfiance,dateVisite,coefNotoriete));				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return praticiens;
	}
	
	
	
public List<Praticien> getListeCoefConfiance() throws ConnexionException {
		
		List<Praticien> praticiens = new ArrayList<Praticien>() ;
		Connection connexion = ConnexionBD.getConnexion();
		Statement stmt;
		try {
			stmt = (Statement) connexion.createStatement();
		
			String requete = "SELECT R1.PRA_NUM,PRA_NOM,PRA_VILLE,RAP_COEFCONFIANCE,RAP_DATE_VISITE,PRA_COEFNOTORIETE "
					+ " FROM RAPPORT_VISITE AS R1 INNER JOIN PRATICIEN "
					+ " ON R1.PRA_NUM = PRATICIEN.PRA_NUM "
					+ "WHERE RAP_DATE_VISITE = "
						+ "(SELECT MAX(RAP_DATE_VISITE) "
						+ "FROM RAPPORT_VISITE AS R2 "
						+ "WHERE R1.PRA_NUM = R2.PRA_NUM  "
						+ "GROUP BY R2.PRA_NUM ) "
					+ "AND RAP_COEFCONFIANCE <=3 "
					+ "ORDER BY RAP_COEFCONFIANCE ASC;";
		
			ResultSet resultat = stmt.executeQuery(requete);
			
			while(resultat.next()){

				Integer numPraticien = resultat.getInt("R1.PRA_NUM");
				String nomPraticien = resultat.getString("PRA_NOM");
				String villePraticien = resultat.getString("PRA_VILLE");
				Integer coefConfiance = resultat.getInt("RAP_COEFCONFIANCE");
				Date dateVisite = resultat.getDate("RAP_DATE_VISITE");
				Float coefNotoriete = resultat.getFloat("PRA_COEFNOTORIETE");
				
				praticiens.add(new Praticien(numPraticien, nomPraticien,villePraticien,coefConfiance,dateVisite,coefNotoriete));				
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return praticiens;
	}


	public List<Praticien> getListeDateVisite() throws ConnexionException {
		
		List<Praticien> praticiens = new ArrayList<Praticien>() ;
		
		Connection connexion = ConnexionBD.getConnexion();
		Statement stmt;
		try {
			stmt = (Statement) connexion.createStatement();
		
	
			String requete = "SELECT R1.PRA_NUM,PRA_NOM,PRA_VILLE,RAP_COEFCONFIANCE,RAP_DATE_VISITE,PRA_COEFNOTORIETE "
					+ " FROM RAPPORT_VISITE AS R1 INNER JOIN PRATICIEN "
					+ " ON R1.PRA_NUM = PRATICIEN.PRA_NUM "
					+ "WHERE RAP_DATE_VISITE = "
						+ "(SELECT MAX(RAP_DATE_VISITE) "
						+ "FROM RAPPORT_VISITE AS R2 "
						+ "WHERE R1.PRA_NUM = R2.PRA_NUM  "
						+ "GROUP BY R2.PRA_NUM ) "
					+ "AND RAP_COEFCONFIANCE <=3 "
					+ "ORDER BY RAP_DATE_VISITE DESC;";
		
			ResultSet resultat = stmt.executeQuery(requete);
			
			while(resultat.next()){
	
				Integer numPraticien = resultat.getInt("R1.PRA_NUM");
				String nomPraticien = resultat.getString("PRA_NOM");
				String villePraticien = resultat.getString("PRA_VILLE");
				Integer coefConfiance = resultat.getInt("RAP_COEFCONFIANCE");
				Date dateVisite = resultat.getDate("RAP_DATE_VISITE");
				Float coefNotoriete = resultat.getFloat("PRA_COEFNOTORIETE");
				
				praticiens.add(new Praticien(numPraticien, nomPraticien,villePraticien,coefConfiance,dateVisite,coefNotoriete));
				
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return praticiens;
	}

	public List<Visiteur> getVisiteurs(String codeRegion) throws ConnexionException{
		
		List<Visiteur >visiteurs = new ArrayList<Visiteur>() ;
	    Connection connexion = ConnexionBD.getConnexion();
	        
	    	try {
	            String requete = "SELECT V1.VIS_MATRICULE , VIS_NOM , VIS_PRENOM, R1.REG_CODE "
	                    + "FROM TRAVAILLER INNER JOIN VISITEUR V1 ON TRAVAILLER.VIS_MATRICULE = V1.VIS_MATRICULE "
	                    + "INNER JOIN REGION R1 ON TRAVAILLER.REG_CODE = R1.REG_CODE " 
	                    + "WHERE TRA_ROLE != 'Delegue' "
	                    + "AND TRA_ROLE != 'Responsable' "
	                    + "AND TRA_ROLE = 'Visiteur' "
	                    + "AND R1.REG_CODE = ?;";
	            
	            PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
	            pstmt.setString(1,codeRegion);
	            ResultSet resultat = pstmt.executeQuery();
	            	            
	            while(resultat.next()){
	
	                String matricule = resultat.getString("V1.VIS_MATRICULE");
	                String nom = resultat.getString("VIS_NOM");
	                String prenom = resultat.getString("VIS_PRENOM");
	                String reg_code = resultat.getString("R1.REG_CODE");
	                
	                visiteurs.add(new Visiteur(matricule, nom, prenom,reg_code));
	            }
	        } 
	        
	        catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	        return visiteurs;
	}
	
	
	
public ArrayList<CompteRendu> getComptesRendus(String matricule, int mois, int annee) throws ConnexionException{
	
		ArrayList<CompteRendu> comptesRendus = new ArrayList<CompteRendu>();
		
		Connection connexion = ConnexionBD.getConnexion();
		
		String requete = "SELECT VIS_MATRICULE, RAP_NUM, PRA_NOM, PRA_PRENOM, PRA_VILLE, RAP_DATE_VISITE , RAP_DATE_CREATION, RAP_EST_LU "
				+ "FROM RAPPORT_VISITE INNER JOIN  PRATICIEN "
				+ "ON RAPPORT_VISITE.PRA_NUM = PRATICIEN.PRA_NUM "
				+ "WHERE  RAP_DATE_VISITE = (SELECT MAX(RAP_DATE_VISITE) "
					+ "FROM RAPPORT_VISITE "
					+ "WHERE VIS_MATRICULE = ? "
					+ "AND MONTH(RAP_DATE_VISITE) = ? "
					+ " AND YEAR(RAP_DATE_VISITE) = ?);"  ;

		try {
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
			
			  pstmt.setString(1, matricule);
			  pstmt.setInt(2,mois);
			  pstmt.setInt(3,annee);

	          System.out.println("Requête : " + pstmt.toString()) ;
	          
	          ResultSet resultat = pstmt.executeQuery();
	          System.out.println("Fin exécution requête") ;
	          
	          if(resultat.next()){
	        	  
	              numCR = resultat.getInt("RAP_NUM");
	              nomPraticien = resultat.getString("PRA_NOM");
	              prenomPraticien = resultat.getString("PRA_PRENOM");
	              villePraticien = resultat.getString("PRA_VILLE");
	              dateVisite = resultat.getDate("RAP_DATE_VISITE");
	              dateCreation = resultat.getDate("RAP_DATE_CREATION");
	              situation = resultat.getString("RAP_EST_LU");
	              String matriculeVisiteur = resultat.getString("VIS_MATRICULE");
	              
	              //On rajoute à la liste le Compte rendu connu
	              comptesRendus.add(new CompteRendu(numCR, nomPraticien, prenomPraticien,villePraticien,dateVisite,dateCreation,situation));
	              
	              System.out.println("Le Compte Rendu : " + matriculeVisiteur+ " "+ numCR + " " + nomPraticien + " " + prenomPraticien 
	          		   + " " + villePraticien + " "+ dateVisite+ " "+ dateCreation + " "+situation);
	              }
	          
	          else{

	        	  numCR = -1; 
	        	  nomPraticien = (String)null;
	        	  prenomPraticien = (String)null;
	        	  villePraticien = (String)null;
	        	  dateVisite = (Date)null;
	        	  dateCreation = (Date)null;
	        	  situation = (String)null;
	        	  
	        	  //On rajoute à la liste le Compte rendu inconnu
	        	  comptesRendus.add(new CompteRendu(numCR, nomPraticien, prenomPraticien,villePraticien,dateVisite,dateCreation,situation));
	              
	              System.out.println("Le Compte Rendu : "+ numCR + " " + nomPraticien + " " + prenomPraticien 
	          		   + " " + villePraticien + " "+ dateVisite+ " "+ dateCreation + " "+situation);
	          }
			} 
		
		catch (SQLException e) {
			e.printStackTrace();
	        System.out.println(e.getErrorCode());
	        System.out.println(e.getSQLState());
	        System.out.println(e.getMessage());
		}
		
		return comptesRendus;
	}
	
	
	
	public String getMatriculeVisiteurs(int mois, int annee,String region) throws ConnexionException{
		
		String matriculeVisiteur = null;
		try{
			

			Connection connexion = ConnexionBD.getConnexion();
			String requete = "SELECT R1.VIS_MATRICULE , T.REG_CODE "
					+ "FROM RAPPORT_VISITE AS R1 INNER JOIN TRAVAILLER AS T "
					+ "ON R1.VIS_MATRICULE = T.VIS_MATRICULE "
					+ "WHERE MONTH(RAP_DATE_VISITE) = ? "
					+ "AND YEAR(RAP_DATE_VISITE)= ? "
					+ "AND T.REG_CODE = ?;";
			
			PreparedStatement	pstmt = (PreparedStatement) connexion.prepareStatement(requete);
			pstmt.setInt(1,mois);
			pstmt.setInt(2,annee);
			pstmt.setString(3,region);

			
			System.out.println("Requête : " + pstmt.toString()) ;
	          
	        ResultSet resultat = pstmt.executeQuery();
	        System.out.println("Fin exécution requête") ;
	          
	          while(resultat.next()){
	        	
	        	 matriculeVisiteur = resultat.getString("VIS_MATRICULE");
	              
	          }
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculeVisiteur;

	}
	
	
	public int compteRenduConsulter(int numeroCompteRendu){
		int resultat = 0  ;
		
		try{

			Connection connexion = ConnexionBD.getConnexion() ;
			String requete = "UPDATE RAPPORT_VISITE SET RAP_EST_LU = 'Lu' WHERE RAP_NUM = ? ;";
			PreparedStatement	pstmt = (PreparedStatement) connexion.prepareStatement(requete);
			pstmt.setInt(1,numeroCompteRendu);
			System.out.println("Requête : " + pstmt.toString()) ;
			resultat = pstmt.executeUpdate();
			System.out.println("Numéro CR :" + " "+numeroCompteRendu );			
		}
		
		catch(Exception e){
			System.out.println("Erreur a la modification du champ est lu : "+e.getMessage());
			
		}
		
		return  resultat; 
	}
	
	
	
	/**
	 * @return the numCR
	 */
	public int getNumCR() {
		return numCR;
	}

	/**
	 * @return the nomPraticien
	 */
	public String getNomPraticien() {
		return nomPraticien;
	}
	
	/**
	 * @return the prenomPraticien
	 */
	public String getPrenomPraticien() {
		return prenomPraticien;
	}
	
	/**
	 * @return the villePraticien
	 */
	public String getVillePraticien() {
		return villePraticien;
	}


	/**
	 * @return the dateVisite
	 */
	public Date getDateVisite() {
		return dateVisite;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}
	
	
	
}
