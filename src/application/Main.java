package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	private static TestiFailiSisu loeFail (File fail) throws Exception {
		// Kõikide küsimuste olemasolu kontroll
    	Nimekiri kysimused = new Nimekiri();
    	List<String> kysimusteList = kysimused.loeNimekiriVeerust(fail, 4, 1);
		// Kõikide valikvastuste olemasolu kontroll
		Nimekiri valikVastused = new Nimekiri();
		List<String> valikvastusteList = valikVastused.loeNimekiriVeerust(fail, 4, 2);	
		// Organisatsioonide või muude lõpptulemuseks saadavate vastusevariantide ja info olemasolu kontroll.
		Organisatsioon organisatsioonid = new Organisatsioon();
		ArrayList<Organisatsioon> tulemused = organisatsioonid.loeOrganisatsioonideAndmed(fail);
		// Testi tutvustuse olemasolu kontroll
		Lahter tutvustus = new Lahter(fail);
		String tutvustusTekst = tutvustus.loeLahter();
		TestiFailiSisu testiFailiSisu = new TestiFailiSisu(kysimusteList, valikvastusteList, tulemused, tutvustusTekst);
		
		return testiFailiSisu;
		
	}
	
	private static TestiFailiSisu salvestaFail(File lahteFail, File sihtFail) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		is = new FileInputStream(lahteFail);
		TestiFailiSisu testiFailiSisu = loeFail(lahteFail);
		os = new FileOutputStream(sihtFail);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
		    os.write(buffer, 0, length);
		}
	    is.close();
		os.close();
		return testiFailiSisu;
    }


	protected TestiFailiSisu testiSisu;
	
	
	@Override
	public void start(Stage pealava) {
		Parool parool = new Parool();
		Top topNr = new Top();
		
		BorderPane piiripaan = new BorderPane();
		
		HBox tekstbox = new HBox();
		tekstbox.setPadding(new Insets(100, 15, 15, 15));
		Text testit = new Text("TESTIT");
		testit.setFont(Font.font ("Bradley Hand ITC", FontWeight.BOLD, 150));
		tekstbox.getChildren().add(testit);
		tekstbox.setAlignment(Pos.CENTER);
		piiripaan.setTop(tekstbox);
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 15, 15, 15));
	    hbox.setSpacing(10);
	    Button nuppLoo = new Button("Loo test");
	    nuppLoo.setPrefSize(500, 100);
	    nuppLoo.setStyle("-fx-font-size: 20pt;");
	    Button nuppTest = new Button("Täida test");
	    nuppTest.setPrefSize(500, 100);
	    nuppTest.setStyle("-fx-font-size: 20pt;");
	    hbox.getChildren().addAll(nuppLoo, nuppTest);
	    hbox.setAlignment(Pos.CENTER);
	    piiripaan.setCenter(hbox);
		
		// Vajutades nuppu "Loo test" avaneb uus aken, kuhu saab sisestada failinime ja ligipääsukoodi.
	    nuppLoo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Esimese akna sulgemine
				pealava.hide();  
				// Luuakse teine lava
				Stage looLava = new Stage();
				BorderPane piiripaanLoo = new BorderPane();
				// TESTIT kujundus ja paigutus
				HBox testitbox = new HBox();
				testitbox.setPadding(new Insets(20, 15, 15, 15));
				Text testit = new Text("TESTIT");
				testit.setFont(Font.font ("Bradley Hand ITC", FontWeight.BOLD, 60));
				testitbox.getChildren().add(testit);
				testitbox.setAlignment(Pos.TOP_CENTER);
				piiripaanLoo.setTop(testitbox);
				
				// Infoteksti kujundus ja paigutus
				VBox infobox = new VBox();
				infobox.setPadding(new Insets(15, 15, 15, 15));
				infobox.setSpacing(24);
				
				Text loomiseInfo = new Text();
				String loomiseInfoTekst = "Testi loomiseks täida allolevad lüngad ning lae üles testi aluseks olev csv fail.";
				loomiseInfo.setText(loomiseInfoTekst);
				loomiseInfo.setFont(Font.font ("Regular", 18));
				
				// Vali kood, millega pääseb vastaja testile ligi.
				GridPane tabelPaigutus = new GridPane();
				tabelPaigutus.setPadding(new Insets(15, 15, 15, 15));
				tabelPaigutus.setVgap(5);
				tabelPaigutus.setHgap(5);
				
				Label info = new Label("Sisesta siia KOOD, millega jõuab vastaja Sinu testini. " +
						"\n" + "Kood peab olema ilma tühikuteta, vähemalt 3 tähemärki ja ei tohi sisaldada tähemärke \\ / : * ? \" < > |" +
						"\n" + "(Sisestatud info kinnitamiseks vajuta ENTER)");
				TextField kood = new TextField();
				Label kuvaKoodiInfo = new Label("Testi ligipääsukood:");
				final Label kuvaKood = new Label();
				kuvaKood.setTextFill(Color.RED);
				GridPane.setConstraints(info, 0, 0);
				GridPane.setColumnSpan(info, 3);
				GridPane.setConstraints(kood, 0, 1);
				GridPane.setColumnSpan(kood, 3);
				GridPane.setConstraints(kuvaKoodiInfo, 0, 2);
				GridPane.setConstraints(kuvaKood, 1, 2);
				
				Label tulemusteInfo = new Label("Sisesta siia arv, mitut parimat tulemust vastaja lõpuks nägema peaks:" +
						"\n" + "(Sisestatud info kinnitamiseks vajuta ENTER)");
				TextField tulemusteTop = new TextField();
				Label kuvaTulemusteTop = new Label("Vastajale on lõpuks nähtav tema tulemuste");
				final Label kuvaTop = new Label();
				kuvaTop.setTextFill(Color.RED);
				GridPane.setConstraints(tulemusteInfo, 0, 4);
				GridPane.setColumnSpan(tulemusteInfo, 3);
				GridPane.setConstraints(tulemusteTop, 0, 5);
				GridPane.setColumnSpan(tulemusteTop, 3);
				GridPane.setConstraints(kuvaTulemusteTop, 0, 6);
				GridPane.setConstraints(kuvaTop, 1, 6);
				
				tabelPaigutus.setAlignment(Pos.CENTER);
				tabelPaigutus.getChildren().addAll(
						info, kood, kuvaKoodiInfo, kuvaKood,
						tulemusteInfo, tulemusteTop, kuvaTulemusteTop, kuvaTop);
				
				kood.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    public void handle(KeyEvent keyEvent) {
				    	if (keyEvent.getCode() == KeyCode.ENTER) {
				    		if ((kood.getText() != null && kood.getText().length() >= 3 && !kood.getText().isEmpty() &&
				    				!kood.getText().contains("\\") && !kood.getText().contains("/") && 
				    						!kood.getText().contains(":") && !kood.getText().contains("*") &&
				    						!kood.getText().contains("?") && !kood.getText().contains("\"") &&
				    						!kood.getText().contains("<") && !kood.getText().contains(">") &&
				    						!kood.getText().contains("|"))) {
				    			String failiKood = kood.getText();
				    			// Kontrollitakse, kas sellise koodiga fail on ehk juba olemas.
				    			FailiKontrollija kt = new FailiKontrollija(failiKood);
				    			if (kt.kasOnFail()) {
				    				Alert alert = new Alert(AlertType.ERROR);
					    			alert.setTitle("Viga!");
					    			alert.setHeaderText("Sellise koodi või võtmesõnaga test on juba olemas! Sisesta uus KOOD!");
					    			alert.showAndWait();
					    			kood.clear();
				    			} else {
				    				parool.setParool(failiKood);
					    			kuvaKood.setText(failiKood);
				    			}
					        } else {
					        	kuvaKood.setText("NB! Lisa testile MIN 3 tähemärgi pikkune ligipääsukood! " +
					        			 "Ära kasuta tähemärke \\ / : * ? \" < > |");
					        }
				    	}
				    }
				});
				
				tulemusteTop.setOnKeyPressed(new EventHandler<KeyEvent>() {
				    public void handle(KeyEvent keyEvent) {
				    	if (keyEvent.getCode() == KeyCode.ENTER) {
				    		try {
					    		if ((tulemusteTop.getText() != null && !tulemusteTop.getText().isEmpty())) {
					    			String top = tulemusteTop.getText();
					    			topNr.setTopNr(Integer.parseInt(top));
						    		kuvaTop.setText("TOP " + top);
						        } else {
						        	kuvaTop.setText("NB! Lisa kasutajale tulemuseks kuvatav paremik!");
						        }
				    		} catch (NumberFormatException e) {
				    			Alert alert = new Alert(AlertType.ERROR);
				    			alert.setTitle("Viga!");
				    			alert.setHeaderText("Sisesta paremiku lahtrisse täisarv!");
				    			alert.showAndWait();
				    		}
				    	}
				    }
				});
				
			    Button nuppValiFail = new Button("Vali testi aluseks olev csv fail ...");
				nuppValiFail.setPrefSize(800, 60);
			    nuppValiFail.setStyle("-fx-font-size: 14pt;");
			    piiripaanLoo.setCenter(nuppValiFail);
			    infobox.getChildren().addAll(loomiseInfo, tabelPaigutus, nuppValiFail);
			    infobox.setAlignment(Pos.TOP_CENTER);
			    piiripaanLoo.setCenter(infobox);
			    
			    FileChooser fileChooser = new FileChooser();
				nuppValiFail.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (parool.getParool() != null && topNr.getTopNr() != 0) {
							// Faili valik ja avamine
							File fail = fileChooser.showOpenDialog(pealava);
							// Koodiks ja faili pealkirjaks on kasutaja poolt sisestatud tekst tekstilahtris.
							String koodNimi = parool.getParool();
							String top = Integer.toString(topNr.getTopNr());
							File failKoopia = new File(koodNimi + "_" + top + ".csv");
			                if (fail != null) {
			                	try {
			                		TestiFailiSisu testiFailiSisu = salvestaFail(fail, failKoopia);
			                		// Juhul kui puuduvaid andmeid ei tuvastata, eemaldatakse lehelt testi loomist puudutav info.
			                		loomiseInfo.setText(" ");
				                	tabelPaigutus.getChildren().removeAll(
				    						info, kood, 
				    						tulemusteInfo, tulemusteTop);
			                		
			                		// Pärast faili sisestamist kuvatav info
					        		VBox tulemusbox = new VBox();
					        		tulemusbox.setPadding(new Insets(15, 15, 15, 15));
					        		tulemusbox.setSpacing(24);
					        		tulemusbox.setAlignment(Pos.TOP_LEFT);
					        		
					        		Line joon = new Line();
					        		joon.setStartX(0.0f);
					        		joon.setStartY(0.0f);
					        		joon.setEndX(1000.0f);
					        		joon.setEndY(0.0f);
					        		
					        		TextArea tekstiala = new TextArea();
					        		tekstiala.setEditable(false);
					        		tekstiala.setWrapText(true);
					        		tekstiala.setMaxWidth(Double.MAX_VALUE);
					        		tekstiala.setMaxHeight(Double.MAX_VALUE);
					        		
					        		Text testiInfo = new Text("Kokkuvõtlik ülevaade testi sisust");
					        		testiInfo.setFont(Font.font ("Regular", 24));				        		
					        		tekstiala.setText(testiFailiSisu.toString(4));
					        		
					        		HBox nupualus = new HBox();
					        		nupualus.setPadding(new Insets(15, 15, 15, 15));
					        	    nupualus.setSpacing(10);
					        	    Button nuppOK = new Button("Valmis");
					        	    nuppOK.setPrefSize(300, 20);
					        	    nuppOK.setStyle("-fx-font-size: 16pt;");
					        	    Button nuppKustutaFail = new Button("Loobu ja kustuta");
					        	    nuppKustutaFail.setPrefSize(300, 20);
					        	    nuppKustutaFail.setStyle("-fx-font-size: 16pt;");
					        	    nupualus.getChildren().addAll(nuppOK, nuppKustutaFail);
					        	    nupualus.setAlignment(Pos.BOTTOM_RIGHT);
					        		
					        		tulemusbox.getChildren().addAll(
					        				joon,
					        				testiInfo,
					        				tekstiala,
					        				nupualus);
					        		piiripaanLoo.setBottom(tulemusbox);
					        		
					        		nuppOK.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent e) {
											looLava.close();
							            }
									});
					        		
					        		nuppKustutaFail.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent e) {
											kood.clear();
											kuvaKood.setText("");
											tulemusteTop.clear();
											kuvaTop.setText("");
											parool.setParool(null);
											topNr.setTopNr(0);
											tulemusbox.getChildren().removeAll(
													joon,
							        				testiInfo,
							        				tekstiala,
							        				nupualus);
											File failitee = failKoopia.getAbsoluteFile();
					            	    	failitee.delete();
					            	    	loomiseInfo.setText(loomiseInfoTekst);
						                	tabelPaigutus.getChildren().addAll(
						    						info, kood, 
						    						tulemusteInfo, tulemusteTop);
							            }
									});
			                		
			                	} catch (tyhiErind e1) {
			                		Alert alert = new Alert(AlertType.ERROR);
					    			alert.setTitle("Viga!");
					    			alert.setHeaderText("Valitud failis on puuduvat infot!");
					    			alert.setContentText(e1.getMessage());
					    			alert.showAndWait();
					    			
			            	    	System.out.println(e1.getMessage());
			            	    	// Vea leidmisel uus loodud fail kustutatakse.
			            	    	File failitee = failKoopia.getAbsoluteFile();
			            	    	failitee.delete();
			            	    } catch (Exception e2) {
			            	    	System.out.println(e2.getMessage());
			            	    }
			                }
						} else {
							Alert alert = new Alert(AlertType.ERROR);
			    			alert.setTitle("Viga!");
			    			alert.setHeaderText("Kontrolli ligipääsukoodi ja paremiku arvu!");
			    			alert.setContentText("Sisesta ilma tühikuteta min 3 tähemärgi pikkune ligipääsukood testile ja täisarvuna vastajale kuvatav tulemuste paremiku hulk.");
			    			alert.showAndWait();
						}
						
		            }
				});
		
		//stseeni loomine ja näitamine		
		Scene stseen2 = new Scene(piiripaanLoo, 800,500);
		looLava.setScene(stseen2);
		looLava.show();
		}});
	    
	    nuppTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Ligipääsukoodi küsimine
				TextInputDialog koodiSisestusKoht = new TextInputDialog();
				koodiSisestusKoht.setTitle("Võtmesõna või ligipääsukood");
				koodiSisestusKoht.setHeaderText("Et jõuaksid õige testini, sisesta võtmesõna või kood, mille oled testi läbiviijalt saanud.");
				koodiSisestusKoht.setContentText("Sisesta testi kood/võtmesõna: ");

				Optional<String> result = koodiSisestusKoht.showAndWait();
				if (result.isPresent()){
					String votmesona = result.get();
					FailiKontrollija koodigaFail = new FailiKontrollija(votmesona);
					if (!koodigaFail.kasOnFail() || koodigaFail.getFailideArv() > 1) {
						Alert alert = new Alert(AlertType.ERROR);
		    			alert.setTitle("Viga!");
		    			alert.setHeaderText("Kontrolli ligipääsukoodi või võtmesõna!");
		    			alert.setContentText("Sellise ligipääsukoodiga testi pole.");
		    			alert.showAndWait();
					} else {
						pealava.hide();
						// Paremiku arvu leidmine faili nimest
						File failitee = koodigaFail.getFailiAadress();
						String failiteeTekstina = failitee.toString();
						int indeks1 = failiteeTekstina.lastIndexOf("_");
						int indeks2 = failiteeTekstina.lastIndexOf(".csv");
						int topArv = Integer.parseInt(failiteeTekstina.substring(indeks1+1, indeks2));
						
						// Testi andmete lugemine tabelist
						try {
							testiSisu = loeFail(failitee);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						// Uus aken ja testi esitamine
						Stage uusLava = new Stage();
                    	BorderPane uusPiiripaan = new BorderPane();
                    	
                    	VBox paigutuskast = new VBox();
                    	paigutuskast.setPadding(new Insets(15, 15, 15, 15));
		        		paigutuskast.setSpacing(24);
		        		paigutuskast.setAlignment(Pos.CENTER);
		        		HBox vastusteKast = new HBox();
		        		vastusteKast.setPadding(new Insets(15, 15, 15, 15));
		        		vastusteKast.setSpacing(24);
		        		vastusteKast.setAlignment(Pos.CENTER);
		        		
		        		Text testiTutvustus = new Text(testiSisu.getTutvustusTekst());
		        		testiTutvustus.setFont(Font.font ("Regular", 24));
		        		testiTutvustus.setWrappingWidth(600);
		        		Button nuppTaitma = new Button("ASU TÄITMA!");
                        nuppTaitma.setPrefSize(500, 100);
                        nuppTaitma.setStyle("-fx-font-size: 20pt;");
		        		
		        		paigutuskast.getChildren().addAll(
		        				testiTutvustus,
		        				nuppTaitma);
		        		uusPiiripaan.setCenter(paigutuskast);
		        		
		        		Scene stseen3 = new Scene(uusPiiripaan, 800,500);
                        uusLava.setScene(stseen3);
                        uusLava.show();
                        
                        nuppTaitma.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                            	paigutuskast.getChildren().removeAll(
                            			testiTutvustus,
        		        				nuppTaitma);
                            	// Küsimuste ja võimalike valikvastuste kuvamine
                            	List<String> kysimused = testiSisu.getKysimusteList();
                            	List<String> valikvastused = testiSisu.getValikvastusteList();
                            	Vastaja vastaja = new Vastaja();
                            	                            	
                            	for(int i = 0; i < kysimused.size(); i++) {
                            		Alert alert = new Alert(AlertType.CONFIRMATION);
                            		alert.setTitle("TESTIT");
                            		alert.setHeaderText(kysimused.get(i));
                            		// alert.setContentText();
                            		String[] valikud = valikvastused.get(i).split(",");
                            		ButtonType[] nupud = new ButtonType[valikud.length];
                            		for(int j = 0; j < valikud.length; j++) {
                            			ButtonType uusnupp = new ButtonType(valikud[j].trim());
                            			nupud[j] = uusnupp;
                            		}
                            		alert.getButtonTypes().setAll(nupud);
                            		Optional<ButtonType> tulemus = alert.showAndWait();
                            		String tulemusStr = tulemus.toString();
                            		int i1 = tulemusStr.indexOf("=");
                            		int i2 = tulemusStr.lastIndexOf(",");
                            		String tulemusTekstina = tulemusStr.substring(i1+1, i2);
                            		vastaja.lisaVastus(tulemusTekstina);
                            	}
                            	// Vastaja tulemuste võrdlemine vastuste profiilidega
                            	ParimValik edetabel = new ParimValik(vastaja.getVastused(), testiSisu.getTulemused());
                            	edetabel.valiParimEdetabel();
                            	String testiTulemus = edetabel.kuvaTop(topArv);
                            	
                            	VBox tulemusteKuva = new VBox();
                            	tulemusteKuva.setPadding(new Insets(15, 15, 15, 15));
                				tulemusteKuva.setSpacing(24);
                            	Text pealkiri = new Text("Sinu testi tulemus:");
                            	pealkiri.setFont(Font.font ("Regular", FontWeight.BOLD, 24));
                            	Text tulemusteTekst = new Text(testiTulemus);
                            	tulemusteTekst.setWrappingWidth(600);
                            	tulemusteTekst.setFont(Font.font ("Regular", 16));
                            	tulemusteKuva.getChildren().addAll(pealkiri,tulemusteTekst);
                            	tulemusteKuva.setAlignment(Pos.CENTER);
                           	
				        		ScrollPane s1 = new ScrollPane(tulemusteKuva);
				        		s1.setFitToHeight(true);
				        		s1.setFitToWidth(true);
				        		uusPiiripaan.setCenter(s1);
				        		
                            }
                        });
                      
					}				      
				}
			}
	    });

		Scene scene = new Scene(piiripaan,600,400);
		pealava.setTitle("TESTIT");
		pealava.setScene(scene);
		pealava.show();
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
