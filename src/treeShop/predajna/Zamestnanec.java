package treeShop.predajna;

import java.io.Serializable;

/**
 * Abstraktna rodicovska trieda <code>Zamestnanec</code> 
 * sluzi na dedenie metod.
 * @author Riccardo Kiss
 *
 */
public abstract class Zamestnanec implements Serializable{
	/**
	 * Vygenerovane <code>serialVersionUID</code> pre triedu <code>Zamestnanec</code>
	 */
	private static final long serialVersionUID = 8331744807321816518L;
	
	protected boolean pohlavie;  // true = muz, false = zena
	protected int vek;

	public Zamestnanec() {

	}

	public Zamestnanec(int vek) {
		this.vek = vek;
	}
	
	public Zamestnanec(boolean pohlavie, int vek) {
		this.pohlavie = pohlavie;
		this.vek = vek;
	}

	public boolean isPohlavie() {
		return this.pohlavie;
	}
	
	public String getPohlavie() {
		return isPohlavie() ? "muž" : "žena";
	}

	public void setPohlavie(boolean pohlavie) {
		this.pohlavie = pohlavie;
	}

	public int getVek() {
		return this.vek;
	}

	public void setVek(int vek) {
		this.vek = vek;
	}
	
	public String info() {
		return "";
	}
	
}
