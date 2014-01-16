package membreSave;

import java.util.*;
import java.text.*;


/**
 * Class Trajet, representant les futurs trajet de covoiturage
 * 
 * @author dashell
 * @version 1.0
 */
public class Trajet implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	protected DatePerso dateTrajet=new DatePerso();
	protected String villeDepart;
	protected String villeArrivee;
	protected Passager chauffeur;
	protected Voiture voiture;
	protected List<Passager> inscrit;

	/**
	 * Constructeur de Trajet n'initialisant pas le Chauffeur du trajet(Trajet en attente d'un chauffeur)
	 * 
	 * @param dateTrajet , date du trajet propos??????
	 * @param villeDepart , ville de depart du trajet
	 * @param villeArrivee , ville d'arriv??????e du trajet
	 * @param heureDepart  , heure de depart du trajet
	 * @param inscrit , liste de passager inscrit au trajet, conducteur non compris.
	 * @throws ParseException 
	 */
	public Trajet(DatePerso dateT,String villeDepart,String villeArrivee) throws ParseException{
		this.dateTrajet=dateT;
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;

		inscrit = new ArrayList<Passager>();
	}

	/**
	 * Constructeur initialisant le chauffeur du trajet(Trajet cr??????e par un conducteur)
	 * 
	 * @param dateTrajet , date du trajet propos??????
	 * @param villeDepart , ville de depart du trajet
	 * @param villeArrivee , ville d'arriv??????e du trajet
	 * @param chauffeur , passager du trajet qui sera conducteur et donc qui fourni la voiture
	 * @param inscrit , liste de passager inscrit au trajet, conducteur non compris.
	 * @throws ParseException 
	 */
	public Trajet(DatePerso dateT,String villeDepart,String villeArrivee,Passager chauffeur) throws ParseException{
		this.dateTrajet=dateT;
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;
		this.chauffeur=chauffeur;
		this.voiture=chauffeur.getVoiture();
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
	 * @return un booleen vrai si le trajet n'??????tait pas plein, faux si il etait plein
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
	/**
	 * methode permettant l'affichage d'un trajet,
	 */
	public String toString(){
		String affichage="Date: "+ dateTrajet.toStringDate() +"\n"+"   Heure: "+dateTrajet.toStringHeure()+"\n"+"   Depart: "+villeDepart+"\n"+"   Arrivee: "+villeArrivee+"\n";
		if(chauffeur==null){
			affichage+="   Chauffeur: Aucun\n";
			affichage+="   En attente d'un chauffeur";
		}else{
			affichage+="   Chauffeur: "+chauffeur.getPseudo()+"\n";
			affichage+="   Voiture : "+voiture.toStringCourt();
			affichage+="   Nombre de Place restante(s): "+nbPlaceRestante()+"\n";
		}
		return affichage;
	}
	/**
	 * methode permettant l'envoi d'une notification signalant la supression d'un trajet

	/**
	 * Renvoi l'affichage court d'un trajet.
	 * @return
	 */
	public String toStringNotif(){
		return "Le trajet de "+villeDepart+" a "+villeArrivee+" le "+dateTrajet.toStringDate()+" est annul??.";
	}
	/**
	 * Methode permettant de retirer un passager inscrit a un trajet
	 * @param p Pasager supprimer du trajet

	/**
	 * Enl??ve le participant p de la liste des participant au trjate.
	 * @param p Participant ?? enlever de la liste.
	 */
	public void enleverParticipant(Passager p){
		inscrit.remove(p);
	}
	/**
	 * methode permettant de verifier si un Passager est deja un participant d'un trajet
	 * @param p Passager dont on test la presence dans les inscrit au trajet
	 * @return vrai si il est deja inscrit faux sinon

	/**
	 * Fonction renvoyant si un Passager est ou n'est pas un participant au trajet.
	 * @param p Participant ?? tester
	 * @return True si p est un participant, false sinon.
	 */
	public boolean estUnParticipant(Passager p){
		for(int i=0; i<inscrit.size(); i++){
			if (inscrit.get(i).getPseudo().equals(p.getPseudo())){
				return true;
			} 
		}
		return false;
	}
	/**
	 * Methode permettant de savoir si un Passger est le conducteur du trajet
	 * @param p passager que l'on test comme conducteur d'un trajet
	 * @return
	 * Fonction renvoyant si un Passager est ou n'est pas le conducteur du trajet.
	 * @param p Participant ?? tester
	 * @return True si p est le conducteur, false sinon.
	 */
	public boolean estLeConducteur(Passager p){
		return chauffeur.getPseudo().equals(p.getPseudo());
	}


	public void addConducteur(Passager connecte) {
		this.chauffeur=connecte;
		this.voiture=connecte.getVoiture();
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

	public Passager getChauffeur() {
		return chauffeur;
	}

	public List<Passager> getInscrit() {
		return inscrit;
	}

}


