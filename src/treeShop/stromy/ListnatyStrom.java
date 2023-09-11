package treeShop.stromy;

/**
 * Trieda <code>ListnatyStrom</code> sluzi na vytvaranie objektov listnatych stromov, ktore sa budu nakupovat.
 * @author Riccardo Kiss
 *
 */
public class ListnatyStrom extends Strom {

	public ListnatyStrom(String rod, double vyska, int vek, double cena) {
		super(rod, vyska, vek, cena);
	}

	public ListnatyStrom(String rod, double cena) {
		super(rod, cena);
	}
}
