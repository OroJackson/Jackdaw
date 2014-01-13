package membre;


public class TestCovoiturage {
	public static void main(String[] args) {
		Covoiturage co=new Covoiturage();
		// Menu de connexion/inscription
		while (co.connecte==null){

			switch(co.menuConnexion()){
			case 1 :
				co.boucleConnexion();
				break;
			case 2 :
				co.inscription();
				break;
			}
		}
		
		
		
	}
}
