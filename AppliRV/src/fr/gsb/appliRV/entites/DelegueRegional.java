package fr.gsb.appliRV.entites;

public class DelegueRegional {

	private String matricule;
	private String codeRegion;

			
	public DelegueRegional(String matricule, String codeRegion) {
		super();
		this.matricule = matricule;
		this.codeRegion = codeRegion;
	}
			
	public String getMatricule() {
		return matricule;
	}

	public String getCodeRegion() {
		return codeRegion;
	}

}
