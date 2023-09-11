package treeShop.zakaznici;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import treeShop.main.LambdaInfo;

/**
 * Trieda <code>Zakaznik</code> sluzi na vytvaranie zakaznikov, 
 * ktori budu nakupovat stromy v predajni a predavat svoje peniaze predavacom.
 * @author Riccardo Kiss
 *
 */
public class Zakaznik {
	private double peniaze;
	private boolean pohlavie;
	private int vek;
	
	public Zakaznik() {

	}
	
	public Zakaznik(double peniaze) {
		this.peniaze = peniaze;
	}
	
	public Zakaznik(boolean pohlavie, int vek, double peniaze) {
		this.pohlavie = pohlavie;
		this.vek = vek;
		this.peniaze = peniaze;
	}
	
	public String getPohlavie() {
		return isPohlavie() ? "muž" : "žena";
	}
	
	public boolean isPohlavie() {
		return pohlavie;
	}

	public void setPohlavie(boolean pohlavie) {
		this.pohlavie = pohlavie;
	}

	public int getVek() {
		return vek;
	}

	public void setVek(int vek) {
		this.vek = vek;
	}

	public double getPeniaze() {
		
		return peniaze;
	}
	
	public void setPeniaze(double peniaze) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		df.applyPattern("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		this.peniaze = Double.parseDouble(df.format(peniaze));		// zaokruhlenie penazi na dve desatinne cisla
	}
	
	/**
	 * Metoda <code>odcitajPeniaze</code> odcita peniaze zakaznikovi za nakup stromu.
	 * @param peniaze suma za nakup stromu
	 */
	public void odcitajPeniaze(double peniaze) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		df.applyPattern("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		this.peniaze -= Double.parseDouble(df.format(peniaze));		// zaokruhlenie penazi na dve desatinne cisla
	}
	
	public String info() {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
		dfs.setGroupingSeparator(' ');
		df.setDecimalFormatSymbols(dfs);
		df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
		
		LambdaInfo infoZakaznik = () -> 
			this.isPohlavie() ? 
				"[Zákazník@" + Integer.toHexString(this.hashCode()) + "]:\t " + this.getVek() + "-ročný " + this.getPohlavie() + " | " + df.format(this.getPeniaze()) + " €\n": 
				"[Zákazníčka@" + Integer.toHexString(this.hashCode()) + "]: " + this.getVek() + "-ročná " + this.getPohlavie() + " | " + df.format(this.getPeniaze()) + " €\n";
	
		return infoZakaznik.vypisInfo();
	}
}
