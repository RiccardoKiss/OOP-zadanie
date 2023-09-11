package treeShop.predajna;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import treeShop.main.LambdaInfo;

/**
 * Trieda <code>Predavac</code> na vytvaranie predavacov, ktory budu preberat peniaze od zakaznikov
 * a posielat ich Uctovnikovi.
 * @author Riccardo Kiss
 *
 */
public class Predavac extends Zamestnanec {
	/**
	 * Vygenerovane <code>serialVersionUID</code> pre triedu <code>Predavac</code>
	 */
	private static final long serialVersionUID = 1358605339177390670L;
	private double peniaze;
	
	public Predavac() {
		super();
	}

	public Predavac(int vek) {
		super(vek);
	}
	
	public Predavac(boolean pohlavie, int vek) {
		super(pohlavie, vek);
	}

	public Predavac(boolean pohlavie, int vek, double peniaze) {
		super(pohlavie, vek);
		this.peniaze = peniaze;	
	}

	public double getPeniaze() {
		return peniaze;
	}

	public void setPeniaze(double peniaze) {
		this.peniaze = peniaze;
	}
	
	/**
	 * Metoda <code>pridajPeniaze</code> pripocita peniaze k aktualnej sume, ktoru vlastni predavac
	 * @param peniaze peniaze Zakaznika z nakupu
	 */
	public void pridajPeniaze(double peniaze) {
		//System.out.println(this.peniaze);
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		df.applyPattern("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		//System.out.println(df.format(peniaze));
		
		this.peniaze += Double.parseDouble(df.format(peniaze));		// zaokruhlenie penazi na dve desatinne cisla
	}
		
	public String info() {
		LambdaInfo infoPredavac = () -> 
			this.isPohlavie() ? 
				"[Predavač@" + Integer.toHexString(this.hashCode()) + "]:\t " + this.getVek() + "-ročný " + this.getPohlavie() + "\n" : 
				"[Predavačka@" + Integer.toHexString(this.hashCode()) + "]: " + this.getVek() + "-ročná " + this.getPohlavie() + "\n";
		
		return infoPredavac.vypisInfo();
	}
}
