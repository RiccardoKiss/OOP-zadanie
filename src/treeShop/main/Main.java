package treeShop.main;

import javafx.application.Application;

/**
 * Trieda <code>Main</code> na spustenie aplikacie.
 * 
 * @author Riccardo Kiss
 * @version 2.0.0 
 * @since May 24, 2020
 */
public class Main {
	/**
	 * Metoda <code>main</code> spusta aplikaciu a zaroven vytvara aj <code>JavaFX Application Thread</code>
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(GUI.class, args);
	}
}
