package treeShop.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Trieda <code>Stopky</code> na meranie casu pocas behu programu. Predstavuje jeden thread.
 * @author Riccardo Kiss
 *
 */
public class Stopky implements Runnable{
	private long start;
	public Stopky() {	
		
	}
	
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	@Override
	public void run() {
		this.setStart(System.currentTimeMillis());
	}
	
	/**
	 * Metoda <code>prejdenyCas</code> vypise cas (v milisekundach), ktory uplynul od spustenia nite.
	 * @return cas, ktory uplynul od spustenia nite [v milisekundach]
	 */
	public long prejdenyCas() {
		long aktualnyCas = System.currentTimeMillis();
		return aktualnyCas - getStart();
	}
	
	/**
	 * Metoda <code>printPrejdenyCas</code> prepise prejdeny cas v milisekundach na tvar "hodiny:minuty:sekundy.milisekundy".
	 * @return vypis
	 */
	public String printPrejdenyCas() {		
		Date date = new Date(prejdenyCas());
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");	// hodiny:minuty:sekundy.milisekundy
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		String vypis = sdf.format(date);
		return vypis;
	}
	
	public void info() {
		System.out.println("Uplynuty cas: " + prejdenyCas() + " ms");
		System.out.println("Uplynuty cas: [" + printPrejdenyCas() + "]\n");
	}
	
	
}
