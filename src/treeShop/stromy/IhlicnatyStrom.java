package treeShop.stromy;

/**
 * Trieda <code>IhlicnatyStrom</code> sluzi na vytvaranie objektov ihlicnatych stromov, ktore sa budu nakupovat.
 * @author Riccardo Kiss
 *
 */
public class IhlicnatyStrom extends Strom {

	public IhlicnatyStrom(String rod, double vyska, int vek, double cena) {
		super(rod, vyska, vek, cena);
	}

	public IhlicnatyStrom(String rod, double cena) {
		super(rod, cena);
	}
}
