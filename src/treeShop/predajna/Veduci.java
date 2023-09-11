package treeShop.predajna;

import java.io.Serializable;

import treeShop.main.LambdaInfo;

public class Veduci extends Zamestnanec {

	/**
	 * Vygenerovane <code>serialVersionUID</code> pre triedu <code>Veduci</code>
	 */
	private static final long serialVersionUID = 5502511639362329858L;

	public Veduci() {
		super();
	}

	public Veduci(int vek) {
		super(vek);
	}

	public Veduci(boolean pohlavie, int vek) {
		super(pohlavie, vek);
	}

	public Predavac najmiPredavaca(boolean pohlavie, int vek) {
		Predavac p = new Predavac(pohlavie, vek);
		return p;
	}

	public Uctovnik najmiUctovnika(boolean pohlavie, int vek) {
		Uctovnik u = new Uctovnik(pohlavie, vek);
		return u;
	}
	
	/**
	 * Metoda vypise vek a pohlavie o instancii triedy <code>Veduci</code>
	 */
	public String info() {
		LambdaInfo infoVeduci = () -> 
		this.isPohlavie() ? 
			"[Vedúci@" + Integer.toHexString(this.hashCode()) + "]:\t " + this.getVek() + "-ročný " + this.getPohlavie() + "\n" : 
			"[Vedúca@" + Integer.toHexString(this.hashCode()) + "]: " + this.getVek() + "-ročná " + this.getPohlavie() + "\n";
	
		return infoVeduci.vypisInfo();
	}

}
