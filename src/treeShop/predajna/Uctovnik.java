package treeShop.predajna;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import treeShop.main.LambdaInfo;
import treeShop.predajna.FiremnyRozpocet;

/**
 * Trieda <code>Uctovnik</code> pre vytvaranie uctovnika v predajni. 
 * Ako jediny ma pristup k {@link treeShop.predajna.FiremnyRozpocet FiremnyRozpocet} a moznost zaobchadzat s nim.
 * 
 * @see treeShop.predajna.FiremnyRozpocet
 *
 */
public class Uctovnik extends Zamestnanec implements Observer {
	/**
	 * Vygenerovane <code>serialVersionUID</code> pre triedu <code>Uctovnik</code>
	 */
	private static final long serialVersionUID = -6831836734102424009L;

	private double peniaze;
	private static FiremnyRozpocet fr = new FiremnyRozpocet(0);

	public Uctovnik() {
		super();
	}
	
	public Uctovnik(int vek) {
		super(vek);
	}
	
	public Uctovnik(boolean pohlavie, int vek) {
		super(pohlavie, vek);
	}

	public Uctovnik(boolean pohlavie, int vek, double peniaze) {
		super(pohlavie, vek);
		this.peniaze = peniaze;
	}

	public double getPeniaze() {
		return peniaze;
	}

	public void setPeniaze(double peniaze) {
		this.peniaze = peniaze;
	}
	
	public static FiremnyRozpocet getFiremnyRozpocet() {
		return fr;
	}

	public static void setFiremnyRozpocet(FiremnyRozpocet fr) {
		Uctovnik.fr = fr;
	}
	
	/**
	 * Metoda pricita peniaze (ktore ma u seba uctovnik) do firemneho rozpoctu.
	 * Nasledne sa vynuluje suma, ktoru ma u seba uctovnik (kedze ich poslal do firemneho rozpoctu).
	 */
	public void pricitajRozpocet() {
		fr.attach(this);
		fr.setRozpocetSuma(fr.getRozpocetSuma() + this.getPeniaze());
		this.setPeniaze(0);
	}
	
	/**
	 * Metoda odcita zadana sumu z firemneho rozpoctu.
	 * Nasledne sa odcitana suma pripocita uctovnikovi (kedze ich zobral z firemneho rozpoctu).
	 * @param suma suma, ktora sa ma odcitat z firemneho rozpoctu
	 */
	public void odcitajRozpocet(int suma) {
		fr.setRozpocetSuma(fr.getRozpocetSuma() - suma);
		this.setPeniaze(suma);
	}
	
	
	
	public String stavRozpoctu() {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
		dfs.setGroupingSeparator(' ');
		df.setDecimalFormatSymbols(dfs);
		df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
		
		if(this.isPohlavie()) return "[Účtovník@" + Integer.toHexString(this.hashCode()) + "]: Firemný Rozpočet = " + df.format(fr.getRozpocetSuma()) + " €\n"; 
		else return "[Účtovníčka@" + Integer.toHexString(this.hashCode()) + "]: Firemný Rozpočet = " + df.format(fr.getRozpocetSuma()) + " €\n";
	}
	
	/**
	 * Metoda vypise vek a pohlavie o instancii triedy <code>Uctovnik</code>
	 */
	public String info() {
		LambdaInfo infoUctovnik = () -> 
		this.isPohlavie() ? 
			"[Účtovník@" + Integer.toHexString(this.hashCode()) + "]:\t " + this.getVek() + "-ročný " + this.getPohlavie() + "\n" : 
			"[Účtovníčka@" + Integer.toHexString(this.hashCode()) + "]: " + this.getVek() + "-ročná " + this.getPohlavie() + "\n";
	
		return infoUctovnik.vypisInfo();
	}
	
}
