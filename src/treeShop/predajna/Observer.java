package treeShop.predajna;

/**
 * Rozhranie <code>Observer</code> pre vyuzitie
 * navrhoveho vzoru Observer v projekte
 * 
 * @author Riccardo Kiss
 *
 */
public abstract interface Observer {
	/**
	 * Metoda vypise aktualny stav firemneho rozpoctu
	 */
	default void update(Uctovnik u) {
		if(u.isPohlavie()) System.out.println("[Účtovník@" + Integer.toHexString(u.hashCode()) + "]: Firemny Rozpocet = " + u.getFiremnyRozpocet().getRozpocetSuma() + " €\n"); 
		else System.out.println("[Účtovníčka@" + Integer.toHexString(u.hashCode()) + "]: Firemny Rozpocet = " + u.getFiremnyRozpocet().getRozpocetSuma() + " €\n");
	
	}
}
