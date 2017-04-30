package fr.gsb.appliRV.entites;

import java.sql.Date;

public class Praticien {

	private int numeroPraticien ;
	private String nomPraticien ;
	private String villePraticien ;
	private int coefConfiance ;
	private Date dateVisite ;
	private float coefNotoriete ;
	
	public Praticien() {
	}

	public Praticien(int numeroPraticien, String nomPraticien,
			String villePraticien, int coefConfiance, Date dateVisite,
			float coefNotoriete) {
		this.numeroPraticien = numeroPraticien;
		this.nomPraticien = nomPraticien;
		this.villePraticien = villePraticien;
		this.coefConfiance = coefConfiance;
		this.dateVisite = dateVisite;
		this.coefNotoriete = coefNotoriete;
	}

	public int getNumeroPraticien() {
		return numeroPraticien;
	}

	public String getNomPraticien() {
		return nomPraticien;
	}

	public String getVillePraticien() {
		return villePraticien;
	}

	public int getCoefConfiance() {
		return coefConfiance;
	}

	public Date getDateVisite() {
		return dateVisite;
	}

	public float getCoefNotoriete() {
		return coefNotoriete;
	}

}