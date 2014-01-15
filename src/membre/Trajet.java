package membre;

import java.util.*;
import java.text.*;


/**
 * Class Trajet, representant les futurs trajet de covoiturage
 * 
 * @author dashell
 * @version 1.0
 */
public class Trajet{
	protected DatePerso dateTrajet=new DatePerso();
	protected String villeDepart;
	protected String villeArrivee;
	protected int heureDepart;
	protected Passager chauffeur;
	protected List<Passager> inscrit;

	/**
	 * Constructeur de Trajet n'initialisant pas le Chauffeur du trajet(Trajet en attente d'un chauffeur)
	 * 
	 * @param dateTrajet , date du trajet propos��
	 * @param villeDepart , ville de depart du trajet
	 * @param villeArrivee , ville d'arriv��e du trajet
	 * @param heureDepart  , heure de depart du trajet
	 * @param inscrit , liste de passager inscrit au trajet, conducteur non compris.
	 * @throws ParseException 
	 */
	public Trajet(String dateT,String villeDepart,String villeArrivee,String heureDepart) throws ParseException{

		this.dateTrajet.toDate(dateT);
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;
		this.dateTrajet.setHeureDepart(heureDepart);
		inscrit = new ArrayList<Passager>();
	}
	
	/**
	 * Constructeur initialisant le chauffeur du trajet(Trajet cr��e par un conducteur)
	 * 
	 * @param dateTrajet , date du trajet propos��
	 * @param villeDepart , ville de depart du trajet
	 * @param villeArrivee , ville d'arriv��e du trajet
	 * @param heureDepart  , heure de depart du trajet
	 * @param chauffeur , passager du trajet qui sera conducteur et donc qui fourni la voiture
	 * @param inscrit , liste de passager inscrit au trajet, conducteur non compris.
	 * @throws ParseException 
	 */
	public Trajet(String dateT,String villeDepart,String villeArrivee,String heureDepart,Passager chauffeur) throws ParseException{

		this.dateTrajet.toDate(dateT);
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;
		this.dateTrajet.setHeureDepart(heureDepart);
		this.chauffeur=chauffeur;
		inscrit = new ArrayList<Passager>();
		
	}

/**
 *  methode permettant de savoir si un trajet est plein ou pas ou s'il attend un conducteur.
 * @return un Int 1 signifiant que le trajet est plein, 0 s'il n'est pas plein, -1 si il est en attente d'un conducteur
 */
	public int estPlein(){
		if (chauffeur!=null){
			if (inscrit.size()==chauffeur.getVoiture().getPlace()){
				return 1;
			}else{
				return 0;
			}
		}else{
			return -1;
		}
	}

/**
 * methode permettant de savoir combien de place il reste(0 si le trajet est plein)
 * @return un int nbPlaceRestante corredpondant au nombre de places restantes.
 */
	public int nbPlaceRestante(){
		if(this.estPlein()==0){
			return chauffeur.getVoiture().getPlace()-inscrit.size();
		}else{
			return 0;
		}	
	}
/**
 * Methode permettant d'ajouter un passager a un trajet
 * @param p : instance de passager a ajouter au trajet
 * @return un booleen vrai si le trajet n'��tait pas plein, faux si il etait plein
 */
	public boolean addParticipant(Passager p){
		if(this.estPlein()==0){
			inscrit.add(p);
			return true;
		}else{
			return false;
		}
	}
/**
 * methode permettant de savoir si le trajet n'a aucun passager
 * @return un booleen vrai si il n'y a aucun inscrit, faut sinon
 */
	public boolean estVide(){
		return inscrit.size()==0;
	}
/**
 * methode permettant de savoir si le trajet a un chauffeur
 * @return un booleen vrai si il y a un chauffeur , faux sinon
 */
	public boolean aUnConducteur(){
		return chauffeur!=null;
	}	
	
	public String toString(){
		String affichage="Date: "+ dateTrajet.toStringDate() +"\n"+"Heure: "+dateTrajet.toStringHeure()+"   Depart: "+villeDepart+"\n"+"   Arrivee: "+villeArrivee+"\n";
				if(chauffeur==null){
					affichage+="   Chauffeur: Aucun\n";
					affichage+="   En attente d'un chauffeur";
				}else{
					affichage+="   Chauffeur: "+chauffeur.getPseudo()+"\n";
					affichage+="   Nombre de Place restante(s): "+nbPlaceRestante()+"\n";
				}
		return affichage;
	}
	
	public String toStringNotif(){
		return "Le trajet de "+villeDepart+" a "+villeArrivee+" le "+dateTrajet+" est annulé.";
	}
	
	public void enleverParticipant(Passager p){
		inscrit.remove(p);
	}
	
	public boolean estUnParticipant(Passager p){
		for(int i=0; i<inscrit.size(); i++){
			if (inscrit.get(i).getPseudo().equals(p)){
				return true;
			} 
		}
		return false;
	}
	
	public boolean estLeConducteur(Passager p){
		return chauffeur.getPseudo().equals(p);
	}
	
	public DatePerso getDateTrajet() {
		return dateTrajet;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public int getHeureDepart() {
		return heureDepart;
	}

	public Passager getChauffeur() {
		return chauffeur;
	}

	public List<Passager> getInscrit() {
		return inscrit;
	}
}


