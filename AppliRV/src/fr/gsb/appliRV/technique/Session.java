package fr.gsb.appliRV.technique;

import fr.gsb.appliRV.entites.DelegueRegional;
import fr.gsb.appliRV.entites.Visiteur;

public class Session {
	
	private static Session session = null ;
	private DelegueRegional leDelegueRegional ;
	
	private Session(DelegueRegional leDelegueRegional){
		super() ;
		this.leDelegueRegional = leDelegueRegional ;
	}
	
	public static void ouvrir(DelegueRegional leDelegueRegional){
		Session.session = new Session(leDelegueRegional) ;
	}
	
	public static void fermer(){
		Session.session = null ;
	}
	
	public static Session getSession(){
		return Session.session ;
	}
	
	public DelegueRegional getLeDelegueRegional(){
		return this.leDelegueRegional ;
	}

	

}
