package treeShop.predajna;

import java.util.ArrayList;

/**
 * Trieda pre firemny rozpocet predajne stromov. 
 * Obsahuje vsetky zarobky(<code>double hlavnyRozpocet</code>) predajne. 
 * 
 * @author Riccardo Kiss
 *
 */
public class FiremnyRozpocet {
	private static double hlavnyRozpocet;
	private ArrayList<Uctovnik> observers = new ArrayList<Uctovnik>();
		
	/**
	 * Verejny konstruktor triedy <code>FiremnyRozpocet</code>
	 * @param rozpocet pociatocna suma, s ktorou vznikne rozpocet predajne
	 */
	public FiremnyRozpocet(double rozpocet) {
		hlavnyRozpocet = rozpocet;
	}

	public double getRozpocetSuma() {
		return hlavnyRozpocet;
	}

	/**
	 * Metoda nastavi novu hodnotu firemneho rozpoctu
	 * @param rozpocet nova suma firemneho rozpoctu
	 */
	public void setRozpocetSuma(double rozpocet) {
		hlavnyRozpocet = rozpocet;
		notifyAllObservers();
	}

	/**
	 * Metoda priradi objekt triedy <code>Uctovnik</code> 
	 * ako observera pre objekt triedy <code>FiremnyRozpocet</code> 
	 * @param observer objekt triedy <code>Uctovnik</code> 
	 */
	public void attach(Uctovnik observer){
	      observers.add(observer);
	      System.out.println(observer);
	   }
	
	/**
	 * Metoda upozorni o zmene stavu objektu <code>FiremnyRozpocet</code> 
	 * vsetkych svojich observerov, ktori su objektami triedy <code>Uctovnik</code> 
	 */
	private void notifyAllObservers() {
		for(Uctovnik observer: observers) 
			observer.update(observer);
	}

}
