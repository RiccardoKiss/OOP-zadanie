package treeShop.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import treeShop.predajna.Predavac;
import treeShop.predajna.Uctovnik;
import treeShop.predajna.Veduci;
import treeShop.stromy.IhlicnatyStrom;
import treeShop.stromy.ListnatyStrom;
import treeShop.zakaznici.Zakaznik;

/**
 * Trieda <code>GUI</code> na vytvorenie grafickeho rozhrania aplikacie.
 * @author Riccardo Kiss
 *
 */
public class GUI extends Application {
	Stopky stopky = new Stopky();
	Thread threadStopky = new Thread(stopky);
	
	public Stage hlavneOkno;
	public Scene scenaVeduci, scenaUctovnik, scenaHlavna;
	
	public GridPane gPaneVeduci = new GridPane();
	public GridPane gPaneUctovnik = new GridPane();
	public GridPane gPaneHlavny = new GridPane();
	
	public Label veduciNadpis = new Label(">Vedúci");
	public Label uctovnikNadpis = new Label(">Účtovník");
	public Label predavacNadpis = new Label(">Predavač");
	public Label zakaznikNadpis = new Label(">Zákazník");
	public Label lPohlavie = new Label("pohlavie:");
	public Label lVek = new Label("vek:");
	public Label lPeniaze = new Label("peniaze:");
	public Label vypisInfo = new Label();
	public Label vypisInfoZakaznik = new Label();
	
	public ComboBox<String> vyberPohlavie = new ComboBox<String>();
	public ComboBox<String> vyberPohlavieZakaznik = new ComboBox<String>();
	
	public TextField vyberVek = new TextField();
	public TextField vyberVekZakaznik = new TextField();
	public TextField vyberPeniazeZakaznik = new TextField();
	public TextArea infoHlavnaScena = new TextArea("Log:\n");
	
	public Button vytvor = new Button("Vytvor");
	public Button vytvorRandom = new Button("Random");
	public Button vytvorZakaznik = new Button("Vytvor");
	public Button vytvorRandomZakaznik = new Button("Random");
	public Button dalsiaScena = new Button("Ďalej");
	public Button bJablon = new Button("Jabloň | 29.90 €");
	public Button bHruska = new Button("Hruška | 31.90 €");
	public Button bSlivka = new Button("Slivka | 35.90 €");
	public Button bBreza = new Button("Breza | 18.90 €");
	public Button bDub = new Button("Dub | 79.90 €");
	public Button bBorovica = new Button("Borovica | 19.90 €");
	public Button bJedla = new Button("Jedľa | 11.90 €");
	public Button bSmrek = new Button("Smrek | 34.90 €");
	public Button bJavor = new Button("Javor | 10.90 €");
	public Button bLipa = new Button("Lipa | 24.90 €");
	public Button bStret = new Button("STRET");
	
	public String myStyle = "-fx-font: 15 monaco";
	public boolean vybratePohlavie, vybratePohlavieZakaznik;
	public int vybratyVek = 0, vybratyVekZakaznik = 0, indexPredavac = 0;
	public double peniazeZakaznik = 0;
	
	/**
	 * Metoda na nastavenie sceny pre vytvaranie Veduceho
	 */
	public void veduci() {
		scenaVeduci = new Scene(gPaneVeduci, 350, 350);
		gPaneVeduci.setHgap(10);
		gPaneVeduci.setVgap(10);
		//gPaneVeduci.setGridLinesVisible(true);  // ukazuje ohranicenia, dobre na vizualny debugging
		
		// labels
		veduciNadpis.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		veduciNadpis.setStyle("-fx-underline: true");
		lPohlavie.setStyle(myStyle);
		lVek.setStyle(myStyle);
		vypisInfo.setStyle(myStyle);
		// inputs
		vyberPohlavie.setPromptText("Výber pohlavia:");
		vyberPohlavie.getItems().addAll("muž", "žena");
		vyberVek.setPromptText("Vložte celé číslo:");
		/*vyberVek.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				
				if(!"0123456789".contains(keyEvent.getCharacter())) {  // akceptuje iba numericke hodnoty, ine znaky ani nejde zapisovat
					keyEvent.consume();
				}
				
			}
		});*/
		// buttons
		vytvor.setStyle(myStyle);
		vytvor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(vyberPohlavie.getValue() != null) {
					try {
						vybratePohlavie = (vyberPohlavie.getValue() == "muž" ? true : false);
						vybratyVek = Integer.parseInt(vyberVek.getText());
						if(vybratyVek >= 18 && vybratyVek <= 70) {  // kazdy zamestnanec musi byt dospely
							if(vybratePohlavie) vypisInfo.setText("[Vedúci]: " + vybratyVek + "-ročný muž");
							else vypisInfo.setText("[Vedúca]: " + vybratyVek + "-ročná žena");
						}
						else vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
					}
					catch(NumberFormatException e) {
						vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
						System.out.println(e.toString());
					}	
				}
				else vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
			}
		});
		vytvorRandom.setStyle(myStyle);
		vytvorRandom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				vybratePohlavie = ThreadLocalRandom.current().nextBoolean();  // nahodne pohlavie
				vybratyVek = ThreadLocalRandom.current().nextInt(18, 71);  // nahodny vek od 18 do 100 rokov
				if(vybratePohlavie) vypisInfo.setText("[Vedúci]: " + vybratyVek + "-ročný muž");
				else vypisInfo.setText("[Vedúca]: " + vybratyVek + "-ročná žena");
			}
			
		});
		dalsiaScena.setStyle(myStyle);  
		dalsiaScena.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				File f = new File("output.txt");
				if(f.length() == 0 && vybratyVek == 0 && vyberVek.getText().isEmpty() && vyberPohlavie.getValue() == null)  // ak su prazdne vstupy
					vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
				else {
					try {
						if(f.length() == 0) {	// ak este nebol vytvoreny ziadny Veduci predtym, tak ho vytvori a ulozi do .txt
							Veduci v = new Veduci(vybratePohlavie, vybratyVek);  // vytvori sa novy Veduci
							//System.out.println(v.info());
							uctovnik(v);  // scena pre vytvaranie uctovnikov
							vypisInfo.setText("");
							hlavneOkno.setScene(scenaUctovnik);  // prepne sa scena
						}
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
							Veduci povodnyVeduci = (Veduci) ois.readObject();
							Uctovnik povodnyUctovnik = (Uctovnik) ois.readObject();
							ois.close();
							System.out.println(povodnyVeduci.info());
							System.out.println(povodnyUctovnik.info());
							hlavnaScena(povodnyVeduci, povodnyUctovnik);  // scena pre stret predavacov a zakaznikov
							vypisInfo.setText("");
							hlavneOkno.setScene(scenaHlavna);  // prepne sa scena
						}
					}
					catch(Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		});
		GridPane.setHalignment(dalsiaScena, HPos.CENTER);  // button zarovna na stred
		// pridanie prvkov do okna
		gPaneVeduci.add(veduciNadpis, 2, 1, 2, 1);
		
		gPaneVeduci.add(lPohlavie, 1, 2);
		gPaneVeduci.add(vyberPohlavie, 2, 2, 2, 1);
		
		gPaneVeduci.add(lVek, 1, 3);
		gPaneVeduci.add(vyberVek, 2, 3, 2, 1);
		
		gPaneVeduci.add(vytvor, 2, 5);
		gPaneVeduci.add(vytvorRandom, 3, 5);
		
		gPaneVeduci.add(vypisInfo, 1, 6, 5, 4);
		gPaneVeduci.add(dalsiaScena, 1, 10, 4, 1);
	}
	
	/**
	 * Metoda na nastavenie sceny pre vytvaranie Uctovnika
	 * @param v objekt Veduci z predchadzajucej sceny sluzi na najimanie Uctovnika
	 */
	public void uctovnik(Veduci v) {
		vybratyVek = 0;
		scenaUctovnik = new Scene(gPaneUctovnik, 350, 350);
		gPaneUctovnik.setHgap(10);
		gPaneUctovnik.setVgap(10);
		//gPaneUctovnik.setGridLinesVisible(true);  // ukazuje ohranicenia, dobre na vizualny debugging
		
		// labels
		uctovnikNadpis.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		uctovnikNadpis.setStyle("-fx-underline: true");
		// inputs - rovnake ako pri vedúcom
		vyberPohlavie = new ComboBox<String>();
		vyberPohlavie.setPromptText("Výber pohlavia:");
		vyberPohlavie.getItems().addAll("muž", "žena");
		vyberVek.setText(null);
		
		// buttons
		vytvor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(vyberPohlavie.getValue() != null) {
					try {
						vybratePohlavie = (vyberPohlavie.getValue() == "muž" ? true : false);
						vybratyVek = Integer.parseInt(vyberVek.getText());
						if(vybratyVek >= 18 && vybratyVek <= 70) {  // kazdy zamestnanec musi byt dospely
							if(vybratePohlavie) vypisInfo.setText("[Účtovník]: " + vybratyVek + "-ročný muž");
							else vypisInfo.setText("[Účtovníčka]: " + vybratyVek + "-ročná žena");
						}
						else vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
					}
					catch(NumberFormatException e) {
						vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
						System.out.println(e.toString());
					}	
				}
				else vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
			}
		});
		vytvorRandom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				vybratePohlavie = ThreadLocalRandom.current().nextBoolean();  // nahodne pohlavie
				vybratyVek = ThreadLocalRandom.current().nextInt(18, 71);  // nahodny vek od 18 do 100 rokov
				if(vybratePohlavie) vypisInfo.setText("[Účtovník]: " + vybratyVek + "-ročný muž");
				else vypisInfo.setText("[Účtovníčka]: " + vybratyVek + "-ročná žena");
			}
			
		});
		dalsiaScena.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				File f = new File("output.txt");
				if(f.length() == 0 && vybratyVek == 0 && vyberVek.getText().isEmpty() && vyberPohlavie.getValue() == null)  // ak su prazdne vstupy
					vypisInfo.setText("Neplatný vstup! Zadajte iné hodnoty!");
				else {
					try {
						if(f.length() == 0) {
							Uctovnik u = v.najmiUctovnika(vybratePohlavie, vybratyVek);	// Veduci vytvori noveho Uctovnika
							ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
							oos.writeObject(v);		// zaznamena objekt Veduci do suboru output.txt
							oos.writeObject(u);		// zaznamena objekt Veduci do suboru output.txt
							oos.close();
							hlavnaScena(v, u);
						}
						else {
							ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
							Veduci povodnyVeduci = (Veduci) ois.readObject();
							Uctovnik povodnyUctovnik = (Uctovnik) ois.readObject();
							ois.close();
							System.out.println(povodnyVeduci.info());
							System.out.println(povodnyUctovnik.info());
							hlavnaScena(povodnyVeduci, povodnyUctovnik);  // scena pre stret predavacov a zakaznikov
							
						}
						vypisInfo.setText("");
						hlavneOkno.setScene(scenaHlavna);  // prepne sa scena
					}
					catch(Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		});
		GridPane.setHalignment(dalsiaScena, HPos.CENTER);  // button zarovna na stred
		// pridanie prvkov do okna
		gPaneUctovnik.add(uctovnikNadpis, 2, 1, 2, 1);
		
		gPaneUctovnik.add(lPohlavie, 1, 2);
		gPaneUctovnik.add(vyberPohlavie, 2, 2, 2, 1);
		
		gPaneUctovnik.add(lVek, 1, 3);
		gPaneUctovnik.add(vyberVek, 2, 3, 2, 1);
		
		gPaneUctovnik.add(vytvor, 2, 5);
		gPaneUctovnik.add(vytvorRandom, 3, 5);
		
		gPaneUctovnik.add(vypisInfo, 1, 6, 5, 4);
		gPaneUctovnik.add(dalsiaScena, 1, 10, 4, 1);
	}
	
	/**
	 * Metoda na nastavenie hlavnej sceny pre nakupovanie stromov zakaznikmi a nasledne posielanie penazi uctovnikom predavacmi.
	 * @param v objekt Veduci sluzi na najimanie Predavacov
	 * @param u objekt Uctovnik sluzi na preberanie penazi od Predavacov a ich a preposlanie do Firemneho Rozpoctu
	 */
	public void hlavnaScena(Veduci v, Uctovnik u) {
		scenaHlavna = new Scene(gPaneHlavny, 1000, 500);
		gPaneHlavny.setHgap(10);
		gPaneHlavny.setVgap(10);
		//gPaneHlavny.setGridLinesVisible(true);  // ukazuje ohranicenia, dobre na vizualny debugging
		GenericList<Zakaznik> listZakaznik = new GenericList<Zakaznik>();	// genericky zoznam zakaznikov
		GenericList<Predavac> listPredavac = new GenericList<Predavac>();	// genericky zoznam predavacov
		ListnatyStrom jablon = new ListnatyStrom("Jabloň", 29.90);
		ListnatyStrom hruska = new ListnatyStrom("Hruška", 31.90);
		ListnatyStrom slivka = new ListnatyStrom("Slivka", 35.90);
		ListnatyStrom breza = new ListnatyStrom("Breza", 18.90);
		ListnatyStrom dub = new ListnatyStrom("Dub", 79.90);
		IhlicnatyStrom borovica = new IhlicnatyStrom("Borovica", 19.90);
		IhlicnatyStrom jedla = new IhlicnatyStrom("Jedľa", 11.90);
		IhlicnatyStrom smrek = new IhlicnatyStrom("Smrek", 34.90);
		ListnatyStrom javor = new ListnatyStrom("Javor", 10.90);
		ListnatyStrom lipa = new ListnatyStrom("Lipa", 24.90);
		
		// labels
		predavacNadpis.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		predavacNadpis.setStyle("-fx-underline: true");
		zakaznikNadpis.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
		zakaznikNadpis.setStyle("-fx-underline: true");
		lPeniaze.setStyle(myStyle);
		vypisInfoZakaznik.setStyle(myStyle);
		infoHlavnaScena.setEditable(false);
		
		// inputs
		vyberPohlavie = new ComboBox<String>();
		vyberPohlavie.setPromptText("Výber pohlavia:");
		vyberPohlavie.getItems().addAll("muž", "žena");
		vyberVek.setText(null);
		vyberPohlavieZakaznik.setPromptText("Výber pohlavia:");
		vyberPohlavieZakaznik.getItems().addAll("muž", "žena");
		vyberVekZakaznik.setPromptText("Vložte celé číslo:");
		// buttons
		vytvorZakaznik.setStyle(myStyle);
		vytvorZakaznik.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(vyberPohlavieZakaznik.getValue() != null) {	// ak bolo zadane pohlavie zakaznika
					try {
						vybratePohlavieZakaznik = (vyberPohlavieZakaznik.getValue() == "muž" ? true : false);
						vybratyVekZakaznik = Integer.parseInt(vyberVekZakaznik.getText());
						peniazeZakaznik = Double.parseDouble(vyberPeniazeZakaznik.getText());
						if(vybratyVekZakaznik >= 15 && vybratyVekZakaznik <= 100) {  // kazdy zakaznik musi mat vek od 15 do 100 rokov
							if(listPredavac.getList().size() <= listZakaznik.getList().size()) {		// ak je menej predavacov ako zakaznikov, nemozem vytvorit dalsieho zakaznika
								vypisInfoZakaznik.setText("Málo predavačov!");
							} 
							else {
								vypisInfoZakaznik.setText("");
								Zakaznik z = new Zakaznik(vybratePohlavieZakaznik, vybratyVekZakaznik, peniazeZakaznik);
								listZakaznik.add(z);
								//vypisInfoZakaznik.setText(z.info());
								infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t" + z.info());
								zakaznikNadpis.setText(">Zákazník " + "(x" + listZakaznik.getList().size() + ")");
							}
						}
						else vypisInfoZakaznik.setText("Neplatný vstup!");
					}
					catch(NumberFormatException e) {
						vypisInfoZakaznik.setText("Neplatný vstup!");
						System.out.println(e.toString());
					}	
				}
				else vypisInfoZakaznik.setText("Neplatný vstup!");
			}
		});
		
		vytvorRandomZakaznik.setStyle(myStyle);
		vytvorRandomZakaznik.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(listPredavac.getList().size() <= listZakaznik.getList().size()) {		// ak je menej predavacov ako zakaznikov, nemozem vytvorit dalsieho zakaznika
					vypisInfoZakaznik.setText("Málo predavačov!");
				} 
				else {
					vypisInfoZakaznik.setText("");
					vybratePohlavieZakaznik = ThreadLocalRandom.current().nextBoolean();  // nahodne pohlavie
					vybratyVekZakaznik = ThreadLocalRandom.current().nextInt(15, 101);  // nahodny vek od 15 do 100 rokov
					peniazeZakaznik = ThreadLocalRandom.current().nextDouble(0, 5001);	// nahodne peniaze od 0 do 5000 €
					
					DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
					df.applyPattern("#.##");
					df.setRoundingMode(RoundingMode.CEILING);
					peniazeZakaznik = Double.parseDouble(df.format(peniazeZakaznik));	// zaokruhlenie penazi na dve desatinne cisla
					
					Zakaznik z = new Zakaznik(vybratePohlavieZakaznik, vybratyVekZakaznik, peniazeZakaznik);
					listZakaznik.add(z);
					//vypisInfoZakaznik.setText(z.info());
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t" + z.info());
					zakaznikNadpis.setText(">Zákazník " + "(x" + listZakaznik.getList().size() + ")");
				}
			}
		});
		
		vytvor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(vyberPohlavie.getValue() != null) {
					try {
						vybratePohlavie = (vyberPohlavie.getValue() == "muž" ? true : false);
						vybratyVek = Integer.parseInt(vyberVek.getText());
						if(vybratyVek >= 18 && vybratyVek <= 70) {  // kazdy zamestnanec musi byt dospely
							vypisInfo.setText("");
							vypisInfoZakaznik.setText("");
							Predavac p = v.najmiPredavaca(vybratePohlavie, vybratyVek);
							listPredavac.add(p);
							//vypisInfo.setText(p.info());
							infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t" + p.info());
							predavacNadpis.setText(">Predavač " + "(x" + listPredavac.getList().size() + ")");
						}
						else vypisInfo.setText("Neplatný vstup!");
					}
					catch(NumberFormatException e) {
						vypisInfo.setText("Neplatný vstup!");
						System.out.println(e.toString());
					}	
				}
				else vypisInfo.setText("Neplatný vstup!");
			}
		});
		vytvorRandom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				vypisInfo.setText("");
				vypisInfoZakaznik.setText("");
				vybratePohlavie = ThreadLocalRandom.current().nextBoolean();  // nahodne pohlavie
				vybratyVek = ThreadLocalRandom.current().nextInt(18, 71);  // nahodny vek od 18 do 100 rokov
				
				Predavac p = v.najmiPredavaca(vybratePohlavie, vybratyVek);
				listPredavac.add(p);
				
				//vypisInfo.setText(p.info());
				infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t" + p.info());
				predavacNadpis.setText(">Predavač " + "(x" + listPredavac.getList().size() + ")");
			}
		});
		
		bJablon.setStyle(myStyle);
		bJablon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= jablon.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(jablon.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(jablon.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bHruska.setStyle(myStyle);
		bHruska.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= hruska.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(hruska.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(hruska.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bSlivka.setStyle(myStyle);
		bSlivka.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= slivka.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(slivka.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(slivka.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bBreza.setStyle(myStyle);
		bBreza.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= breza.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(breza.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(breza.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bDub.setStyle(myStyle);
		bDub.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= dub.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(dub.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(dub.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bBorovica.setStyle(myStyle);
		bBorovica.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= borovica.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(borovica.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(borovica.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bJedla.setStyle(myStyle);
		bJedla.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= jedla.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(jedla.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(jedla.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bSmrek.setStyle(myStyle);
		bSmrek.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= smrek.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(smrek.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(smrek.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bJavor.setStyle(myStyle);
		bJavor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= javor.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(javor.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(javor.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bLipa.setStyle(myStyle);
		bLipa.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (listZakaznik.getList().size() > 0) {
					infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t");
					if(listZakaznik.getList().get(listZakaznik.getList().size()-1).isPohlavie()) {
						infoHlavnaScena.appendText("[Zákazník@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]:\t ");
					} else {
						infoHlavnaScena.appendText("[Zákazníčka@" + Integer.toHexString(listZakaznik.getList().get(listZakaznik.getList().size()-1).hashCode()) + "]: ");
					}
					if (listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze() >= lipa.getCena()) {
						listZakaznik.getList().get(listZakaznik.getList().size()-1).odcitajPeniaze(lipa.getCena());
						listPredavac.getList().get(listZakaznik.getList().size()-1).pridajPeniaze(lipa.getCena());
						
						DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
						DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
						dfs.setGroupingSeparator(' ');
						df.setDecimalFormatSymbols(dfs);
						df.applyPattern("###,###.00");	// na vypisovanie penazi v tvare s oddelenymi tisickami a dvomi desatinnymi miestami
						
						infoHlavnaScena.appendText("Nákup úspešný | Zostatok: " + df.format(listZakaznik.getList().get(listZakaznik.getList().size()-1).getPeniaze()) + " €\n");
					} else {
						infoHlavnaScena.appendText("Nedostatok peňazí na nákup!\n");
					}
				} else {
					vypisInfoZakaznik.setText("Neexistuje zákazník!");
				}
			}
		});
		bStret.setStyle(myStyle);
		bStret.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				for (int i = 0; i < listPredavac.getList().size(); i++) {		// kazdy predavac posle zarobene peniaze uctovnikovi
					u.setPeniaze(u.getPeniaze() + listPredavac.getList().get(i).getPeniaze());
					listPredavac.getList().get(i).setPeniaze(0);
				}
				u.pricitajRozpocet();	// uctovnik ulozi peniaze do firemneho rozpoctu
				
				infoHlavnaScena.appendText("[" + stopky.printPrejdenyCas() + "]\t" + u.stavRozpoctu());
				
				listZakaznik.getList().clear();		// vymaze vsetkych zakaznikov
				zakaznikNadpis.setText(">Zákazník " + "(x" + listZakaznik.getList().size() + ")");
			}
		});
		
		// pridanie prvkov do okna
		gPaneHlavny.add(zakaznikNadpis, 2, 1, 4, 1);
		gPaneHlavny.add(predavacNadpis, 8, 1, 4, 1);
		
		gPaneHlavny.add(lPohlavie, 1, 2);
		gPaneHlavny.add(vyberPohlavieZakaznik, 2, 2, 2, 1);
		gPaneHlavny.add(vyberPohlavie, 8, 2, 2, 1);
		
		gPaneHlavny.add(lVek, 1, 3);
		gPaneHlavny.add(vyberVekZakaznik, 2, 3, 2, 1);
		gPaneHlavny.add(vyberVek, 8, 3, 2, 1);
		
		gPaneHlavny.add(lPeniaze, 1, 4);
		gPaneHlavny.add(vyberPeniazeZakaznik, 2, 4, 2, 1);
		
		gPaneHlavny.add(vytvorZakaznik, 2, 6);
		gPaneHlavny.add(vytvorRandomZakaznik, 3, 6);
		gPaneHlavny.add(vytvor, 8, 6);
		gPaneHlavny.add(vytvorRandom, 9, 6);
		
		gPaneHlavny.add(vypisInfoZakaznik, 2, 7, 2, 3);
		gPaneHlavny.add(vypisInfo, 8, 7, 2, 3);
		gPaneHlavny.add(infoHlavnaScena, 1, 10, 10, 5);
		gPaneHlavny.add(bStret, 1, 15, 2, 1);
		
		gPaneHlavny.add(bJablon, 11, 10, 2, 1);
		gPaneHlavny.add(bHruska, 11, 11, 2, 1);
		gPaneHlavny.add(bSlivka, 11, 12, 2, 1);
		gPaneHlavny.add(bBreza, 11, 13, 2, 1);
		gPaneHlavny.add(bDub, 11, 14, 2, 1);
		gPaneHlavny.add(bBorovica, 13, 10, 2, 1);
		gPaneHlavny.add(bJedla, 13, 11, 2, 1);
		gPaneHlavny.add(bSmrek, 13, 12, 2, 1);
		gPaneHlavny.add(bJavor, 13, 13, 2, 1);
		gPaneHlavny.add(bLipa, 13, 14, 2, 1);
	}
	
	/**
	 * Metoda <code>start</code> na spustenie behu grafickeho rozhrania a stopovania casu v pozadi pocas behu aplikacie
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			threadStopky.start();
			
			hlavneOkno = primaryStage;
			hlavneOkno.setTitle("TreeShop_v1.0");
			veduci();  // scena pre vytvaranie veduceho
			
			
			hlavneOkno.setScene(scenaVeduci);
			hlavneOkno.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
