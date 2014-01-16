package membre;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

public class TestCovoiturage {
	private static Covoiturage co;
	
	public static void main(String[] args) throws ParseException {		
		//deserialize();
		co= new Covoiturage();
		boolean quitter=false;
		

		
		// Menu de connexion/inscription
		while(!quitter){
			while (co.connecte==null && !quitter){
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
				//serialize();
			}
		}
	}
	
	private static void serialize() {
        try {
                FileOutputStream fileOut = new FileOutputStream("data");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(co);
                out.close();
                fileOut.close();
        }
        catch(IOException i) {
                i.printStackTrace();
        }
	}
	
	private static void deserialize() throws ParseException {
        File file = new File("data");
        if(file.exists()) {
                try {
                        FileInputStream fileIn = new FileInputStream(file);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        co = (Covoiturage) in.readObject();
                        in.close();
                        fileIn.close();
                }
                catch(IOException i) {
                        i.printStackTrace();
                }
                catch(ClassNotFoundException c) {
                        c.printStackTrace();
                }
        }
        else {
                co = new Covoiturage();
        }
	}
}
