package treeShop.stromy;

/**
 * Abstraktna rodicovska trieda <code>Strom</code> 
 * sluzi na dedenie metod.
 * @author Riccardo Kiss
 *
 */
public abstract class Strom {
	protected String rod;
	protected double vyska, cena;
	protected int vek;
	
	public Strom(String rod, double vyska, int vek, double cena) {
		this.rod = rod;
		this.vyska = vyska;
		this.vek = vek;
		this.cena = cena;
	}
	
	public Strom(String rod, double cena) {
		this.rod = rod;
		this.cena = cena;
	}

	public String getRod() {
		return rod;
	}

	public void setRod(String rod) {
		this.rod = rod;
	}
	
	public double getVyska() {
		return vyska;
	}

	public void setVyska(double vyska) {
		this.vyska = vyska;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getVek() {
		return vek;
	}

	public void setVek(int vek) {
		this.vek = vek;
	}

}
