package com.techelevator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.JDBC.CampGroundJDBCDAO;
import com.techelevator.JDBC.CampSiteJDBCDAO;
import com.techelevator.JDBC.ParkJDBCDAO;
import com.techelevator.JDBC.ReservationJDBCDAO;

public class CampgroundCLI {
	private static final String WELCOME = "WELCOME TO OUR NATIONAL PARK RESERVATION SYSTEM";
	private static final String VIEW_PARKS = "PLEASE SELECT A PARK";

	private static final String VIEW_PARK_INFO = "VIEW PARK INFORMATION";
	private static final String VIEW_CAMPGROUNDS = "VIEW CAMPGROUNDS";
	private static final String BOOK_RESERVATIONS = "BOOK RESERVATIONS";
	private static final String GO_BACK = "GO BACK TO PREVIOUS SCREEN";
	private static final String[] SECOND_MENU_OPTIONS = {VIEW_PARK_INFO, 
			VIEW_CAMPGROUNDS,
			BOOK_RESERVATIONS,
			GO_BACK
	};
	private static final String SEARCH_RESERVATIONS = "SEARCH FOR AVAILABLE RESERVATION";
	private static final String[] THIRD_MENU_OPTIONS = {SEARCH_RESERVATIONS,
			GO_BACK
	};

	private static final String WHICH_CAMPGROUND = "WHICH CAMPGROUND (enter 0 to cancel)? ";
	private static final String WHAT_ARRIVAL_DATE = "WHAT IS THE ARRIVAL DATE(YYYY-MM-DD)?";
	private static final String WHAT_DEPART_DATE = "WHAT IS THE DEPARTURE DATE(YYYY-MM-DD)? ";

	private static final String WHAT_SITE_RESERVED = "WHAT SITE SHOULD BE RESERVED (enter 0 to cancel)?";
	private static final String WHAT_NAME_RESERVED = "WHAT NAME SHOULD THE RESERVATION BE UNDER?";
	private static final String RESERVATION_NUMBER = "THE RESERVATION HAS BEEN MADE AND THE CONFIRMATION ID IS ";

	private Menu menu;

	private ParkDAO daoP;
	private CampGroundDAO daoCG;
	private CampSiteDAO daoCS;
	private ReservationDAO daoR;
	private String choice2;


	public static void main(String[] args) {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();

	}

	public CampgroundCLI(DataSource datasource) {
		// create your DAOs here
		daoP = new ParkJDBCDAO(datasource);
		daoCG = new CampGroundJDBCDAO(datasource);
		daoCS = new CampSiteJDBCDAO(datasource);
		daoR = new ReservationJDBCDAO(datasource);
	}

	public void run() {
		menu = new Menu(System.in, System.out);
		System.out.println(WELCOME);
		System.out.println(VIEW_PARKS);

		//produces first menu in which you select a park
		while(true){

			Park choice = (Park)menu.getChoiceFromOptions(daoP.getAllParks().toArray());
			//		System.out.println(QUIT);
			for(int i = 0; i < daoP.getAllParks().size(); i++){
				Long indexParkIdValue = daoP.getAllParks().get(i).getParkId();

				if(indexParkIdValue.equals(choice.getParkId())){
					while(true){

						//produces second (4 choice) menu where you can view park info, view campgrounds, book reservations and go back

						String choice2 = (String)menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);

						if(choice2.equals(VIEW_PARK_INFO)){
							System.out.print(choice.getParkName() + "\n" 
									+ String.format("%-20s", "Location: ") + choice.getParkLocation() + "\n" 
									+ String.format("%-20s", "Established: ") + choice.getParkEstablishDate() + "\n" 
									+ String.format("%-20s", "Area: ") + choice.getParkArea() + " Acres" + "\n" 
									+ String.format("%-20s", "Annual Visitors: ") + choice.getParkVisitors() + "\n");
						}
						if(choice2.equals(VIEW_CAMPGROUNDS)){
							System.out.println(String.format("%-50s", "Name") + String.format("%-20s", "Open") + String.format("%-20s", "Close") + String.format("%-20s", "Daily Fee")); 
							for(int j = 0; j < daoCG.getAllCampgrounds(choice.getParkId()).size(); j++){
								System.out.print(String.format(j+1 + ". " + "%-50s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundName())  +
										String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenFromMonth()) +
										String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenToMonth()) +
										String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundDailyFee()) + "\n");	
							}

							//this is menu if user chooses (view campgrounds)

							String choice3 = (String)menu.getChoiceFromOptions(THIRD_MENU_OPTIONS);
							//						while(true){
							if(choice3.equals(SEARCH_RESERVATIONS)){
								System.out.println("\n" + SEARCH_RESERVATIONS + "\n");
								System.out.println(String.format("%-50s", "Name") + String.format("%-20s", "Open") + String.format("%-20s", "Close") + String.format("%-20s", "Daily Fee")); 
								for(int j = 0; j < daoCG.getAllCampgrounds(choice.getParkId()).size(); j++){
									System.out.print(j+1 + ". " + String.format("%-50s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundName())  +
											String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenFromMonth()) +
											String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenToMonth()) +
											String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundDailyFee()) + "\n");	
								}

								// variables created outside if statements to be used in our select statement when we find campsites

								CampGround selectedCampGround = new CampGround();
								CampSite selectedSite = new CampSite();
								LocalDate localDate1;
								LocalDate localDate2;
								Scanner input1 = new Scanner(System.in);
								int campgroundChoice;
								int selectedSiteChoice;
								// menu to choose what campsites are available

								while(true){
									System.out.println("\n" + WHICH_CAMPGROUND);

									try{
										campgroundChoice = Integer.parseInt(input1.nextLine()); 
										if(campgroundChoice == 0){
											break;

										}
										else{
											selectedCampGround = daoCG.getAllCampgrounds(choice.getParkId()).get(campgroundChoice - 1);
											break;
										}

									}catch(NumberFormatException ex){
										System.out.println("Not a valid number, try again");
									}catch(IndexOutOfBoundsException ex){
										System.out.println("Number not within range, try again");
									}catch(InputMismatchException ex){
										System.out.println("Not a number, try again");
									}

								}

								if(campgroundChoice == 0){
									break;
								} 
								while(true){ 
									System.out.print("\n" + WHAT_ARRIVAL_DATE);
									try {
										String Arriveinput = input1.nextLine();

										localDate1 =LocalDate.parse(Arriveinput);
										break;	
									} catch (Exception e) {
										System.out.println("Invalid Date, please try again...");
									}

								}

								while(true){
									System.out.print(WHAT_DEPART_DATE);
									try {
										String departInput = input1.nextLine();

										localDate2 =LocalDate.parse(departInput);
										break;	
										//Date arriveDate = menu.getDateFromUserInput(input);
									} catch (Exception e) {
										System.out.println("Invalid Date, please try again...");
									}
								}

								List<CampSite> CSList = daoCS.getCampSitesByUserInput(selectedCampGround, localDate1, localDate2);
								while(true){
									System.out.println("\n SITES AVAILABLE \n");
									System.out.println(String.format("%-20s", "Site Number") + String.format("%-20s", "Max Occupancy") + String.format("%-20s","Accessible") + String.format("%-20s","Max RV Length") + String.format("%-20s","Utility")+ String.format("%-20s","Cost"));
									for(int x = 0; x < daoCS.getCampSitesByUserInput(selectedCampGround, localDate1, localDate2).size(); x++){
										System.out.println(x+1 + ". " + String.format("%-20s",CSList.get(x).getCampsiteId()) + String.format("%-20s", CSList.get(x).getCampsiteMaxOccupancy())
										+ String.format("%-20s",CSList.get(x).isCampsiteAccessible()) + String.format("%-20s",CSList.get(x).getCampsiteMaxRVLength()) + 
										String.format("%-20s",CSList.get(x).isCampsiteUtilities()) + String.format("%-20s",selectedCampGround.getCampgroundDailyFee())
												);
									}
									break;
								}

								while(true){
									System.out.println(WHAT_SITE_RESERVED);
									try{
										selectedSiteChoice = Integer.parseInt(input1.nextLine()); 
										if(selectedSiteChoice == 0){
											break;
										}
										else{
											selectedSite = CSList.get(selectedSiteChoice - 1);
											break;
										}
									}catch(NumberFormatException ex){
										System.out.println("Not a valid number, try again");
									}catch(IndexOutOfBoundsException ex){
										System.out.println("Number not within range, try again");
									}catch(InputMismatchException ex){
										System.out.println("Not a number, try again");
									}
								}
								if(selectedSiteChoice == 0){
									break;
								}

								System.out.println(WHAT_NAME_RESERVED);
								String reserveName = input1.nextLine();
								Long reservationId = daoR.makeReservation(reserveName, selectedSite, localDate1, localDate2);
								System.out.println(RESERVATION_NUMBER + reservationId);

								//this break will push you back to 2nd menu with view park info, view campgrounds, book reservations, etc...
								break;

							}



						}
						//break off 2nd menu when you choose Number 4 (GO BACK TO PREVIOUS SCREEN). Brings back to main menu
						else{
							break;
						}
					}
				}
			}
		}

	}



}

