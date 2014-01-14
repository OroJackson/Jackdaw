package membre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Trajet, representant les futurs trajet de covoiturage
 * 
 * @author dashell
 * @version 1.0
 */
public class Trajet{
	protected GregorianCalendar dateTrajet;

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
	public Trajet(String dateT,String villeDepart,String villeArrivee,int heureDepart) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("dd:MM:yy");
		Date date = format.parse(dateT);
		GregorianCalendar dateTrajet = new GregorianCalendar();
		dateTrajet.setTime(date);
		this.dateTrajet=dateTrajet;
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;
		this.heureDepart=heureDepart;
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
	public Trajet(String dateT,String villeDepart,String villeArrivee,int heureDepart,Passager chauffeur) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("dd:MM:yy");
		Date date = format.parse(dateT);
		GregorianCalendar dateTrajet = new GregorianCalendar();
		dateTrajet.setTime(date);
		this.dateTrajet=dateTrajet;
		this.villeDepart=villeDepart;
		this.villeArrivee=villeArrivee;
		this.heureDepart=heureDepart;
		this.chauffeur=chauffeur;
		inscrit = new ArrayList<Passager>();

	}

/**
 *  methode permettant de savoir si un trajet est plein ou pas
 * @return un booleen vrai si le trajet n'as plus de place, faut si il en reste
 */
	public boolean estPlein(){
		return inscrit.size()==chauffeur.getVoiture().getPlace();
	}

/**
 * methode permettant de savoir combien de place il reste(0 si le trajet est plein)
 * @return un int nbPlaceRestante corredpondant au nombre de places restantes.
 */
	public int nbPlaceRestante(){
		if(!this.estPlein()){
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
		if(!this.estPlein()){
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
	
	public GregorianCalendar getDateTrajet() {
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


