package membreSave;


import java.text.*;
import java.util.*;

/**
 * Classe Covoiturage. Regroupe l'ensemble des fonctionnalit?? du covoiturage
 * 
 * @author Aurie
 * @version 1.0
 */
public class Covoiturage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Trajet> trajets = new ArrayList<Trajet>();
	private List<Passager> membres = new ArrayList<Passager>();
	private HashMap<String,Passager> pseudoMdp = new HashMap<String,Passager>();
	private Set<String> pseudos = new HashSet<String>();
	Passager connecte;
	
	
	public Covoiturage() throws ParseException{
		Passager tmp=new Passager("admin",null,null,null,null,"mdp");
		membres.add(tmp);
		pseudos.add(tmp.pseudo);
		pseudoMdp.put(tmp.getPseudo(), tmp);
		tmp.ajouterVoiture(new Voiture("Ferrari","Rouge",5,1));
		tmp= new Passager("Dashell","Galas","Alain","galasx@gmail.com",null,"mdp");
		membres.add(tmp);
		pseudos.add(tmp.pseudo);
		pseudoMdp.put(tmp.getPseudo(), tmp);
		tmp= new Passager("Aurie","Digeon","Aur??lie","aurelie.digeon@gmail.com",null,"mdp");
		membres.add(tmp);
		pseudos.add(tmp.pseudo);
		pseudoMdp.put(tmp.getPseudo(), tmp);
		tmp.ajouterVoiture(new Voiture("C3","Blanche",5,4));

		devCreationTrajetACChauffeur(new DatePerso("12:13:2014"),"test1","test1",tmp);
		devCreationTrajetACChauffeur(new DatePerso("12:13:2014"),"test2","test3",tmp);
		devCreationTrajetACChauffeur(new DatePerso("12:13:2014"),"test3","test4",tmp);
	}
	
	private Scanner sc= new Scanner (System.in);
	/**
	 * M??thode r??alisant l'inscription d'un membre dans le Covoiturage
	 */
	public void inscription (){
		
		String pseudo="";
		do {
			System.out.print("Pseudo :");
			pseudo=sc.nextLine();
			if (verifierUnicite(pseudo)){
				System.out.println("Ce pseudo est d??j?? utilis??.");
			}
		} while (verifierUnicite(pseudo));
		System.out.print("Mot de Passe :");
		String mdp=sc.nextLine();
		System.out.print("Nom :");
		String nom= sc.nextLine();
		System.out.print("Pr??nom :");
		String prenom= sc.nextLine();
		String email="";
		do {
			System.out.print("Email :");
			email= sc.nextLine();
			if (!verifMail(email)){
				System.out.println("Adresse email incorrect.");
			}
		} while (!verifMail(email));
		
		String telephone="";
		do {
			System.out.print("Telephone :");
			telephone= sc.nextLine();
			if (!verifTelephone(telephone)){
				System.out.println("Telephone incorrect.");
			}
		} while (!verifTelephone(telephone));

		
		Passager p=new Passager(pseudo,nom,prenom,email,telephone,mdp);
		membres.add(p);
		pseudos.add(p.getPseudo());
		pseudoMdp.put(p.getPseudo(), p);
	
		// Verifier la validit?? de l'email
		// Verifier t??l??phone
		
		System.out.println("\nVous voil?? inscript sur le Jackdaw !");
		System.out.println("Vous ??tes passager, pour ajouter une voiture, allez dans votre profil");
		System.out.println("Bon Voyages "+ prenom +"!");
	}
	/**
	 *V??rifie que le String est bien un num??ro de t??l??phone.(Commence par un 0, 10 chiffres)
	 * @param telephone String ?? v??rifier
	 * @return true si c'est correct, false sinon.
	 */
	public boolean verifTelephone(String telephone){
		return telephone.charAt(0)=='0' && isNumeric(telephone) && telephone.length()==10;
	}
	
	/**
	 * V??rifie qu'un string soit un num??rique.
	 * @param telephone String ?? v??rifier
	 * @return true si c'est bien un num??rique, false sinon.
	 */
	public boolean isNumeric(String telephone){  
	  try  {  
	    double d = Double.parseDouble(telephone);
	  } catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	 * M??thode v??rifiant que le mail est dans le bon format (au moins un char puis un '@' puis au moins 2 char puis un '.' et enfin 2char).
	 * @param a Adresse mail ?? v??rifier
	 * @return true si l'adresse est correct, false sinon.
	 */
	public boolean verifMail(String a){
		boolean valide=false;
		for(int i=1 ; i<(a.length()) ; i++) {
			 if (a.charAt(i)=='@') {
				 if (i<(a.length()-4)){
					 for (int k=i ; k<(a.length()-2) ; k++) 
						 if (a.charAt(k)=='.') valide = true;
				 }
			 }
		}
		return valide;
	}
	/**
	 * Fonction v??rifiant si le pseudo est unique ou non dans le programme.
	 * @param pseudo Pseudo dont on souhaite v??rifier l'unicit?? dans le programme.
	 * @return True si le pseudo est d??j?? dans le programme, false sinon.
	 */
	public boolean verifierUnicite(String pseudo){
		return pseudos.contains(pseudo);
	}
	
	/**
	 * M??thode de connexion au Covoiturage.
	 * 
	 * @return false si la connexion c'est bien pass??,True sinon.
	 */
	public boolean connexion(){
		System.out.print("Identifiant : ");
		String pseudo= sc.nextLine();
		System.out.print("Mot de passe : ");
		String mdp = sc.nextLine();
		System.out.println();
		
		if(pseudos.contains(pseudo) && pseudoMdp.get(pseudo).getMdp().equals(mdp)){
			connecte=pseudoMdp.get(pseudo);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * M??thode donnant le choix a l'utilisateur de r????sayer la connexion ou de revenir au Menu
	 */
	public void boucleConnexion(){
		boolean situation= false;
		while (!situation){
			situation = connexion();
			if (!situation){
				System.out.println("Identifiants ou Mdp incorrect");
				System.out.println("1. Re??ssayer\n2. Retour menu");
				if (sc.nextInt()==2){
					situation=true;
				}
				sc.nextLine();
			}
		}
	}
	
	/**
	 * M??thode r??alisant la d??connexion d'un membre du Covoiturage.
	 */
	public void deconnexion(){
		connecte=null;
	}
	/**
	 * Menu proposant ?? l'utilisateur de se connecter ou de s'inscrire
	 * @return Le num??ro de la r??pond : 1 pour connexion ou 2 pour inscription.
	 */
	public int menuConnexion(){
		System.out.println("--------------------------");
		System.out.println("Bienvenue sur le Jackdaw.\n");
		System.out.println("1. Connexion");
		System.out.println("2. S'inscrire");
		System.out.println("\n3.Quitter.\n");
		System.out.print("Votre choix : ");
		
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		System.out.println("--------------------------");

		return reponse;
	}
	
	/**
	 * M??thode destin??e ?? ajouter une voiture dans la liste de voiture d'un passager. Elle lui demande le modele, la couleur, le nombre de place et le confort de la voiture.
	 * @param p Passager auquel on souhaite ajouter une voiture.
	 */
	public void ajoutVoiture(Passager p){
		
		System.out.println("Mod??le de la voiture :");
		String modele= sc.nextLine();
		System.out.println("Couleur de la voiture :");
		String couleur= sc.nextLine();
		System.out.println("Confort de la voiture (sur 5):");
		int confort= sc.nextInt();
		while (confort<0 || confort>5){
			sc.nextLine();
			System.out.println("Le confort se note sur 5");
			confort= sc.nextInt();
			sc.nextLine();
		}

		System.out.println("Nombre de place de la voiture:(hors conducteur)");
		int nbPlaces=sc.nextInt();
		while (nbPlaces<1 || nbPlaces>8 ){
			sc.nextLine();
			System.out.println("Le nombres de place est compris entre 1 et 8");
			nbPlaces= sc.nextInt();
			sc.nextLine();
		}
		p.ajouterVoiture(new Voiture(modele,couleur,confort,nbPlaces));
	}
	/**
	 * M??thode permettant de demander a l'utilisateur diff??rent renseignement puis de creer le trajet.
	 * @throws ParseException
	 */
	public void creationTrajet() throws ParseException{

		DatePerso dateTrajet = new DatePerso();
		boolean dateOK=false,heureOK=false;
		while (!dateOK){
			System.out.println("Date du Trajet? (JJ:MM:AAAA)");
			String dateT =sc.nextLine();
			try{
				dateTrajet.toDate(dateT);
				dateOK=true;
			}catch(ParseException p){
				p.printStackTrace();
				System.out.println("\n");
				dateOK= false;
				System.out.println("Erreur de saisie pour la Date, veuillez recommencez(JJ:MM:AAAA)");
			}
		}
		System.out.println("Ville de depart?");
		String villeDepart=sc.nextLine();
		System.out.println("Ville d'arrivee?");
		String villeArrivee=sc.nextLine();
		while (!heureOK){
			System.out.println("Heure de depart du trajet ?(HH:MM)");
			String heureD=sc.nextLine();
			try{
				dateTrajet.setHeureDepart(heureD);
				heureOK=true;
			}catch(ParseException p){

				//System.err.println(p);
				p.printStackTrace();
				heureOK= false;
				System.out.println("Erreur de saisie pour l'heure, veuillez recommencez(HH:MM)");
			}
		}

		System.out.println("Etes vous le conducteur de ce trajet ?(o/n)");
		String rep=sc.nextLine();
		while(rep.equals("o") && rep.equals("n")){
			System.out.println("Veuillez repondre par 'o' ou 'n'");
			rep=sc.nextLine();
		}
		if(rep.equals("o")){
			Trajet courant=new Trajet(dateTrajet,villeDepart,villeArrivee,connecte);
			trajets.add(courant);
			connecte.addTrajet(courant);
		}else{
			Trajet courant =new Trajet(dateTrajet,villeDepart,villeArrivee);
			courant.addParticipant(connecte);
			trajets.add(courant);
			connecte.addTrajet(courant);
		}

	}


	
	/**
	 * M??thode de develloppement pour creer rapidement un trajet avec chauffeur.
	 * @param d Date du trajet.
	 * @param villeD Ville de d??part du trajet.
	 * @param villeA Ville d'arriv??e du trajet.
	 * @param p Chauffeur du trajet.
	 * @throws ParseException
	 */
	public void devCreationTrajetACChauffeur(DatePerso d,String villeD,String villeA,Passager p) throws ParseException{
		Trajet t =new Trajet(d,villeD,villeA,p);
		trajets.add(t);
		p.addTrajet(t);
	}
	/**	 
	 * M??thode de develloppement pour creer rapidement un trajet sans chauffeur.
	 * @param d Date du trajet.
	 * @param villeD Ville de d??part du trajet.
	 * @param villeA Ville d'arriv??e du trajet
	 * @throws ParseException
	 */
	public void devCreationTrajetSSChauffeur(DatePerso d,String villeD,String villeA) throws ParseException{
		Trajet t =new Trajet(d,villeD,villeA);
		trajets.add(t);
	}
	/**
	 * Demande a l'utilisateur une ville de d??part et lance la recherche des tout les trajets depuis cette Ville, puis les affiches.
	 */
	public void chercherTrajet(){
		System.out.print("Entrez la ville de d??part : ");
		String villeD = sc.nextLine();
		System.out.println();
		
		List<Trajet> trajetsRecherche =chercherTrajetAvecVilleDepart(villeD);
		if (trajetsRecherche.size()==0){
			System.out.println("Aucun trajet ne correspond ?? votre demande.");
		} else {
			for (int i=0; i<trajetsRecherche.size(); i++){
				System.out.println((i+1) +"-"+trajetsRecherche);
			}
			menuTrajetRecherche(trajetsRecherche);
		}
	}
	/**
	 * Menu proposant a l'utilisateur de s'inscrire ?? un des trajets de la liste pass??e en param??tre ou de retourner au menu principal.
	 * @param trajetsRecherche Listes des trajets utilisable dans le menu.
	 */
	public void menuTrajetRecherche(List<Trajet> trajetsRecherche){
		System.out.println("1. S'inscrire ?? un trajet.");
		System.out.println("2. Retour.");
		System.out.print("Votre choix : ");
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse==1){
			System.out.println("(Taper 0 pour quitter)");
			System.out.println("Num??ro du trajet : ");
			System.out.print("Votre choix : ");
			reponse = sc.nextInt();
			sc.nextLine();
			System.out.println();
			while (reponse<0 || reponse >trajetsRecherche.size()){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse = sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse!=0){
				boolean conducteur=false;
				Trajet courant= trajetsRecherche.get(reponse-1);
				if (courant.estUnParticipant(connecte) || courant.estLeConducteur(connecte)){
					System.out.println("Vous etes d??j?? inscrit ?? ce trajet.");
				} else if (courant.estPlein()==1){
					System.out.println("Le trajet est plein.");
				} else {
					if(!courant.aUnConducteur() && connecte.estUnConducteur()){
						System.out.println("Voulez-vous vous incrire en tant que conducteur ?(o/n)");
						String choix =sc.nextLine();
						if (choix.equals("o")){
							conducteur=true;
						} 
					} 
					if (conducteur){
						courant.addConducteur(connecte);
						connecte.addTrajet(courant);
					} else {
						courant.addParticipant(connecte);
						connecte.addTrajet(courant);
					}
					System.out.println("Vous ??tes maintenant inscrit ?? ce trajet.");
				}
			}
		}
	}
	/**
	 * Recherche les trajets ayant pour ville de d??part VilleD.
	 * @param villeD Ville de d??part dont on cherche tout les trajets.
	 * @return Une liste de tout les trajets ayant comme ville de d??part villeD.
	 */
	public List<Trajet> chercherTrajetAvecVilleDepart(String villeD){
		List<Trajet> trajetsRecherche = new ArrayList<Trajet>();
		for (int i=0; i<trajets.size(); i++){
			if (trajets.get(i).getVilleDepart().equals(villeD)){
				trajetsRecherche.add(trajets.get(i));
			}
		}
		return trajetsRecherche;
	}
	
	/**
	 * Affiche le profil de l'utilisateur connect??. Puis propose ?? l'utilisateur de voir ses voitures, d'ajouter une voiture, d'en supprimer une, de modifier son mot de passe ou de revenir au menu principal.
	 */
	public void monProfil(){
		System.out.println(connecte);
		System.out.println("\nSouhaitez vous :");
		System.out.println("1. Voir vos voitures.");
		System.out.println("2. Ajouter une voiture");
		System.out.println("3. Supprimer une voiture");
		System.out.println("4. Choisir voiture principal.");
		System.out.println("5. Modifier votre mot de passe");
		System.out.println("\nVotre choix : ");
		int rep=sc.nextInt();
		System.out.println();
		
		sc.nextLine();
		switch(rep){
		case 1:
			System.out.println(connecte.afficherVoitures());
			break;
		case 2:
			ajoutVoiture(connecte);
			break;
		case 3:
			supprimerVoiture();
			break;
		case 4 :
			choisirVoiturePrincipal();
			break;
		case 5:
			changeMdp();
			break;
		}
	}
	/**
	 * M??thode affichant d'abord la liste des voitures puis demande ?? l'utilisateur quel voiture il souhaite choisir en voiture principal et met cette voiture en voiture principal.
	 */
	private void choisirVoiturePrincipal() {
		System.out.println(connecte.afficherVoitures());
		if (connecte.nbVoitures()!=0){
			System.out.println("\n(Pour annuler taper 0)");
			System.out.print("Entrez le num??ro de la voiture principale:");
			int reponse = sc.nextInt();
			sc.nextLine();
			while (reponse<0 || reponse>connecte.nbVoitures()){
				System.out.println("Ce num??ro ne correspond pas ?? une de vos voitures.");
				System.out.println("Num??ro de la voiture : ");
				reponse = sc.nextInt();
				sc.nextLine();
			
			}
			if (reponse!=0){
				connecte.setVoiturePrincipale(connecte.getVoitures().get(reponse-1));
				System.out.println("Voiture "+ reponse+" est maintenant votre voiture principal.");
			}
		}
	}
	/**
	 * M??thode servant ?? changer le mot de passe de l'utilisateur connect??. Il lui demande d'abord son ancien mdp puis le nouveau.
	 */
	private void changeMdp() {
		System.out.print("Quelle est votre mot de passe actuel: ");
		String rep=sc.nextLine();
		if(rep.equals(connecte.getMdp())){
			System.out.print("\nEntrez un nouveau mot de passe: ");
			rep=sc.nextLine();
			System.out.println();
			connecte.setMdp(rep);
		} else {
			System.out.println("Mot de passe incorrect.");
		}
	}

	/**
	 * M??thode supprimant une voiture de l'utilisateur connect?? : il lui affiche d'abord toutes ses voitures puis lui demande laquelle il veut supprimer.
	 */
	private void supprimerVoiture() {
		System.out.println(connecte.afficherVoitures());
		if (connecte.nbVoitures()!=0){
			System.out.println("\n(Pour annuler taper 0)");
			System.out.print("Entrez le num??ro de la voiture ?? supprimer :");
			int reponse = sc.nextInt();
			sc.nextLine();
			while (reponse<0 || reponse>connecte.nbVoitures()){
				System.out.println("Ce num??ro ne correspond pas ?? une de vos voitures.");
				System.out.println("Num??ro de la voiture ?? supprimer : ");
				reponse = sc.nextInt();
				sc.nextLine();
			
			}
			if (reponse!=0){
				connecte.supprimerVoiture(connecte.getVoitures().get(reponse-1));
				System.out.println("Voiture "+ reponse+" ?? ??t?? supprim??e.");
			}
		}
	}
	/**
	 * M??thode demandant ?? l'utilisateur s'il souhaite annuler un trajet, se desincrire ou retourner au menu pr??c??dent.
	 */
	public void menuTrajet(){
		System.out.println("--------------------------");
		System.out.println("1. Annuler un trajet. (Conducteur)");
		System.out.println("2. Se desinscrire. (Passager)\n");
		System.out.println("3. Retour.\n");
		
		System.out.print("Votre choix : ");
		
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		switch(reponse){
		case 1 :
			annulerTrajet();
			break;
		case 2 : 
			desinscriptionTrajet();
			break;
		case 3 :
			break;
		}
	}
	/**
	 * Fonction qui demande ?? l'utilisateur quel trajet il souhaite annuler et lance l'annulation.
	 */
	public void annulerTrajet(){
		System.out.println("Entrez le num??ro du Trajet que vous voulez supprimer.");
		System.out.println("Pour annuler taper 0");
		System.out.println("(ATTENTION Tout ceux qui ??tait inscrit a votre Trajet recevront un message de suppression)\n");
		
		System.out.print("Votre choix : ");
		int reponse= sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse>0){
			while (reponse>connecte.nbDeTrajets() && reponse>0){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse= sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse>0){
				if (connecte.trajetA(reponse-1).getChauffeur().getPseudo().equals(connecte.getPseudo())){
					envoyerMessageSuppression(connecte.trajetA(reponse-1));
					connecte.supprimerTrajet(connecte.trajetA(reponse-1));
					System.out.println("Le trajet "+ reponse+" a ??t?? supprim??.");
				} else {
					System.out.println("Vous etes passager. \nUtiliser l'option 'Se desinscrire'.");
				}
			}	
		}
	}
	/**
	 * Envoi dans la List message de chaque membre un message pour signaler que le trajet est annul??.
	 * @param t Trajet annul??
	 */
	public void envoyerMessageSuppression(Trajet t){
		List<Passager> inscrit = t.getInscrit();
		for (int i=0; i<inscrit.size(); i++){
			inscrit.get(i).ajouterMessage(t.toStringNotif());
		}
	}
	/**
	 * Demande ?? l'utilisateur de quel trajet il veut se desinscrire et lance la desincription
	 */
	public void desinscriptionTrajet(){
		System.out.println("Entrez le num??ro du Trajet dont vous voulez vous desinscrire.");
		System.out.println("Pour annuler taper 0");
		
		System.out.print("Votre choix : ");
		int reponse= sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse>0){
			while (reponse>connecte.nbDeTrajets() && reponse>0){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse= sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse>0){
				if (!connecte.trajetA(reponse-1).getChauffeur().getPseudo().equals(connecte.getPseudo())){
					connecte.trajetA(reponse-1).enleverParticipant(connecte);
				} else {
					System.out.println("Vous etes le conducteur. \nPour supprimer utiliser l'option 'Annuler Trajet'.");
				}
			}	
		}
	}
	
	/**
	 * Affiche le menu principal et demande ?? l'utilisateur s'il veut chercher un trajet, creer un trajet, afficher ses trajets, afficher son profil et se deconnecter.
	 * @throws ParseException
	 */
	public void menuPrincipal() throws ParseException{
		System.out.println("--------------------------");
		if (connecte.afficherMessage().equals("")){
			System.out.println("Vous n'avez pas de nouveau message");
		} else {
			System.out.println(connecte.afficherMessage());
		}
		System.out.println("--------------------------");

		System.out.println("1. Creer un trajet.");
		System.out.println("2. Chercher un trajet.");
		System.out.println("3. Mes trajets.");
		System.out.println("4. Mon profil.");
		System.out.println("5. D??connexion.");
		System.out.print("\nVotre choix : ");
		int rep=sc.nextInt();
		System.out.println();
		System.out.println("--------------------------");

		sc.nextLine();
		switch(rep){
		case 1:
			creationTrajet();
			break;
		case 2:
			chercherTrajet();
			break;
		case 3:
			connecte.afficherMesTrajets();
			menuTrajet();
			break;
		case 4:
			monProfil();
			break;
		case 5:
			System.out.println("Aurevoir "+connecte.getPrenom());
			connecte=null;
			break;
		}
	}
}