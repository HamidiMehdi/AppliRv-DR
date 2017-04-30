package fr.gsb.appliRV.entites;

import java.sql.Date;

public class Visiteur {
	
	private String matricule ;
	private String nom ; 
	private String prenom ;
	private String adresse ;
	private int cp ;
	private String ville ;
	private Date dateEmbauche ;
	private String section ;
	private String laboration ;
	private String login ;
	private String mdp ;
	private String code_region ;

	public Visiteur(String matricule, String nom, String prenom,String adresse, int cp, String ville, 
					Date date,String section, String laboration, String login, String mdp) {
		
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.dateEmbauche = date;
		this.section = section;
		this.laboration = laboration;
		this.login = login;
		this.mdp = mdp;
	}

	public Visiteur(String matricule, String nom, String prenom){
		this.matricule = matricule ;
		this.nom = nom ;
		this.prenom = prenom ;
	}
	
	public Visiteur(String matricule, String nom, String prenom, String code_region){
		this.matricule = matricule ;
		this.nom = nom;
		this.prenom = prenom ;
		this.code_region =code_region;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
