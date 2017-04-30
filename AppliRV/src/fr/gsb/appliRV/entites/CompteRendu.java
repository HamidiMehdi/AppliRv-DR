package fr.gsb.appliRV.entites;

import java.sql.Date;

public class CompteRendu {

	private int numeroCR ;
	private String nomPraticien ;
	private String prenomPraticien ;
	private String villePraticien ;
	private Date dateVisiteCR ;
	private Date dateCreationCR ;
	private String situation ;

	/**
	 * @param numeroCR
	 * @param nomPraticien
	 * @param prenomPraticien
	 * @param villePraticien
	 * @param dateVisiteCR
	 * @param dateCréationCR
	 * @param situation
	 */
	public CompteRendu(int numeroCR, String nomPraticien,
			String prenomPraticien, String villePraticien, Date dateVisiteCR,
			Date dateCréationCR, String situation) {
		super();
		System.out.println("CompteRendu::CompteRendu(int,String,String,String;String,Date,Date,String)");
		this.numeroCR = numeroCR;
		this.nomPraticien = nomPraticien;
		this.prenomPraticien = prenomPraticien;
		this.villePraticien = villePraticien;
		this.dateVisiteCR = dateVisiteCR;
		this.dateCreationCR = dateCréationCR;
		this.situation = situation;
	}

	
	/**
	 * @return the numeroCR
	 */
	public int getNumeroCR() {
		return numeroCR;
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
	 * @return the dateVisiteCR
	 */
	public Date getDateVisiteCR() {
		return dateVisiteCR;
	}

	/**
	 * @return the dateCreationCR
	 */
	public Date getDateCreationCR() {
		return dateCreationCR;
	}

	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompteRendu [numeroCR=" + numeroCR + ", nomPraticien="
				+ nomPraticien + ", prenomPraticien=" + prenomPraticien
				+ ", villePraticien=" + villePraticien + ", dateVisiteCR="
				+ dateVisiteCR + ", dateCreationCR=" + dateCreationCR
				+ ", situation=" + situation + "]";
	}
}
