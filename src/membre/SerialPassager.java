package membre;

import java.io.Serializable;

public class SerialPassager extends Passager implements Serializable {
    public SerialPassager(){
    	super();
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
    public void SerialPassager(Passager passagerToClone) {
     this.pseudo=passagerToClone.getPseudo();
     this.nom=passagerToClone.getNom();
     this.prenom=passagerToClone.getPrenom();
    }

}
