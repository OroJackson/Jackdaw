package membre;

import java.text.ParseException;


public class TestCovoiturage {
	public static void main(String[] args) throws ParseException {
		boolean quitter=false;
		Covoiturage co=new Covoiturage();
		
		
		// Menu de connexion/inscription
		while(!quitter){
			while (co.connecte==null){

				switch(co.menuConnexion()){
				case 1 :
					co.boucleConnexion();
					break;
				case 2 :
					co.inscription();
					break;
				case 3 :
					quitter=true;
					break;
				}
			}
			while(co.connecte!=null && !quitter){
				co.menuPrincipal();
			}
		}
	}
}
