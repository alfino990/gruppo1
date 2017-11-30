package it.gruppo1;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StatisticheDB {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");

			try {
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/statistiche", "root",
						"juve");
while(true) {
				int scelta;

				System.out.println("Benvenuti in statistiche");
				System.out.println();
				System.out.println("Premi 0 per inserire un risultato (Squadra_Casa, Squadra_Ospite, Goal_Casa, Goal_Ospite)");
				System.out.println();
				System.out.println("Premi 1 per visualizzare tutti i pareggi");
				System.out.println();
				System.out.println("Premi 2 per visualizzare i pareggi della tua squadra");
				System.out.println();
				System.out.println("Premi 3 per visualizzare le vittore esterne della tua squadra");
				System.out.println();
				System.out.println("Premi 4 per visualizzare i goal segnati dalla tua squadra in casa ");
				System.out.println();
				System.out.println("Premi 5 per visualizzare gli incontri del campionato selezionato");
				System.out.println();
				System.out.println("Premi 6 per visualizzare la classifica del campionato");
				System.out.println();
				
				
				scelta = sc.nextInt();
				boolean flag = true;
				while (flag) {
					switch (scelta) {

					
					case 0:
						Scanner inserimento = new Scanner(System.in);
						Scanner inserimento1 = new Scanner(System.in);
						Scanner inserimento2 = new Scanner(System.in);
						Scanner inserimento3 = new Scanner(System.in);
						flag=false;
						System.out.println("Inserisci squadra casa: ");
						String squadra_casa=inserimento.nextLine();
						
						System.out.println("Inserisci squadra ospite: ");
						String squadra_ospite = inserimento1.nextLine();
					
						System.out.println("Inserisci goal casa: ");
						int goal_casa = inserimento2.nextInt();
				
						System.out.println("Inserisci goal ospite: ");
						int goal_ospite = inserimento3.nextInt();
				
					
					
						
						PreparedStatement ps = c.prepareStatement("INSERT INTO incontro ( squadra_casa,squadra_ospite,goal_casa,goal_ospite,competizione) VALUES(?,?,?,?,?) ");
						
						ps.setString(1, squadra_casa);
						ps.setString(2, squadra_ospite);
						ps.setInt(3, goal_casa);
						ps.setInt(4, goal_ospite);
						ps.setString(5, "serie_a");
						ps.executeUpdate();
						break;
					
					
					
					case 1:
						flag = false;
						Statement s = c.createStatement();
						ResultSet rs = s.executeQuery("SELECT squadra_casa, squadra_ospite, goal_casa, goal_ospite FROM incontro WHERE incontro.goal_casa = incontro.goal_ospite;");
						while (rs.next()) {
								for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								System.out.println(rs.getString(i));

							}
						}

						break;

					case 2:
						flag = false;
						System.out.println("Inserisci il nome della tua squadra");
						String squadra = sc1.nextLine();
						
										
						Statement ss = c.createStatement();
						ResultSet rss = ss.executeQuery("SELECT squadra_casa , squadra_ospite, goal_casa, goal_ospite "
								+ "FROM incontro"
								+ " WHERE (incontro.goal_casa = incontro.goal_ospite) AND squadra_casa = "+"'"+squadra+"'"+" "
										+ "OR (incontro.goal_casa = incontro.goal_ospite) AND squadra_ospite ="+ "'" + squadra + "'"+"  ;");
						
											while (rss.next()) {
							
											for (int i = 1; i <= rss.getMetaData().getColumnCount(); i++) {
											System.out.println(rss.getString(i)); 
											
							}
									
													
											
						}
						
						sc1.close();
						
						break;

					case 3:
						flag = false;
						System.out.println("Inserisci il nome della tua squadra");
						String sq = sc1.nextLine();
						
										
						Statement s3 = c.createStatement();
						ResultSet rs3 = s3.executeQuery("SELECT squadra_casa , squadra_ospite, goal_casa, goal_ospite FROM incontro WHERE incontro.goal_casa < incontro.goal_ospite AND squadra_ospite ="+ "'" + sq + "'"+"  ;");
						while (rs3.next()) {
											for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
											System.out.println(rs3.getString(i)); 

							}
						}
						
						
						
						break;

					case 4:
						flag = false;
						System.out.println("Inserisci il nome della tua squadra");
						String sqd = sc1.nextLine();
						
										
						Statement s4 = c.createStatement();
						ResultSet rs4 = s4.executeQuery("SELECT sum(incontro.goal_casa) AS goal  FROM incontro WHERE  squadra_casa ="+ "'" + sqd + "'"+"  ;");
						while (rs4.next()) {
											for (int i = 1; i <= rs4.getMetaData().getColumnCount(); i++) {
											System.out.println("Goal totali " + sqd 
													+ " in casa: "+rs4.getString(i)); 

							}
						}
						
						
						
						break;

					case 5:
						flag = false;
						System.out.println("Inserisci il nome del campionato ");
						String cpt = sc1.nextLine();
						if(cpt.equals("serie a"))
							cpt="serie_a";
										
						Statement s5 = c.createStatement();
						ResultSet rs5 = s5.executeQuery("SELECT squadra_casa, squadra_ospite, goal_casa,goal_ospite FROM incontro WHERE competizione ="+ "'" + cpt + "'"+"   ;");
						System.out.println("Incontri "+cpt+" sono:");
						while (rs5.next()) {
							
											for (int i = 1; i <= rs5.getMetaData().getColumnCount(); i++) {
												
											System.out.println(rs5.getString(i)); 

							}
						}
												
						break;
						
					case 6:
						flag = false;
			
										
						Statement s6 = c.createStatement();
						ResultSet rs6 = s6.executeQuery("SELECT * FROM classifica");
						System.out.println("La classifica è: ");
						while (rs6.next()) {
							
											for (int i = 1; i <= rs6.getMetaData().getColumnCount(); i++) {
												
											System.out.println(rs6.getString(i)); 

							}
						}
												
						break;
						
						
						
					default:
						System.out.println("Inserisci un numero presente nel menu");
						scelta = sc.nextInt();
					}

				}

				/*
				 * Statement s = c.createStatement(); ResultSet rs =
				 * s.executeQuery("SELECT * FROM incontro;");
				 * 
				 * while(rs.next()) { System.out.println("Row "+rs.getRow()); for(int i = 1; i <
				 * rs.getMetaData().getColumnCount(); i++) {
				 * System.out.println(rs.getMetaData().getColumnLabel(i)+": " // stampa il nome
				 * della colonna i +rs.getString(i)); // stampa il contenuto della cella i
				 * rs.getMetaData().getColumnLabel(i).toString(); } }
				 * 
				 */
}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
