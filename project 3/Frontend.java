// --== CS400 File Header Information ==--
// Name: Brian Blinder
// Email: bblinder@wisc.edu
// Team: KC Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Frontend {
	private static int turn;

	/**
	 * Main method that begins the program by calling the intro method
	 * 
	 * @param args represents arguments for the main
	 * @throws IOException         if the file being read is invalid in any way
	 * @throws DataFormatException if the data from the file is formated incorrectly
	 * @throws URISyntaxException  if the string(file name) in cannot be parse when creating a new game
	 */
	public static void main(String[] args) throws IOException, DataFormatException, URISyntaxException {
		intro();//calls intro method
	}

	/**
	 * Intro of the game, is responsbile for starting the game If user enters x,
	 * game will exit If user enters s, game will start
	 *
	 * @throws IOException         if the file being read is invalid in any way
	 * @throws DataFormatException if the data from the file is formated incorrectly
	 * @throws URISyntaxException  if the string(file name) in cannot be parse when creating a new game
	 */
	private static void intro() throws IOException, DataFormatException, URISyntaxException {
		Scanner input = new Scanner(System.in);//inits. scanner for the intro method input from console
		//User Interface Building
		System.out.println(
				"************************************************************************************************************************************************************");
		System.out.print("Hi, Welcome to the Wikipedia Seach Game!  |");
		
		String feedback;//var for the user input
		
		//loops until proper input is given, if 's' is entered the game Starts, if 'x' is entered the game exits
		do {
			System.out.println("  To start enter 's'  |  Otherwise enter 'x' to exit the program!");
			feedback = input.next();
			if (feedback.equals("x")) {
				gameExit();
			} else if (feedback.equals("s")) {
				gameStart();
			} else {
				System.out.println("Invalid character entered.");
			}
		} while (!feedback.equals("x") || !feedback.equals("s"));
		
		//User Interface Building
		System.out.println(
				"************************************************************************************************************************************************************");
	}

	/**
	 * Start of the game, is responsbile for the pregame process, and running the
	 * game When the user find his target page, the will present a final screen In
	 * the screen he user can press 'p', to play again or 'x' to exit the game
	 *
	 * @throws IOException         if the file being read is invalid in any way
	 * @throws DataFormatException if the data from the file is formated incorrectly
	 * @throws URISyntaxException  if the string(file name) in cannot be parse when creating a new game
	 */
	private static void gameStart() throws IOException, DataFormatException, URISyntaxException {
		Backend backend = new Backend("links.tsv");//As the game starts a new backend variable is made with the list provided
		
		//User Interface Building
		System.out.println(
				"************************************************************************************************************************************************************");
		Scanner gameInput = new Scanner(System.in);//scanner to get user input from console
		
		//User Interface Building
		System.out.print("To play the game: ");
		System.out.print("Enter the title of the Wikipedia article that you want to start at  |");
		System.out.print("  Enter 'r' if you want a random starting article  |");
		System.out.println("  Enter 'x' to exit.");
		String start = gameInput.nextLine();//variable to hold the start wikipedia location
		
		//if the start var is an 'x' the game exits, 'r' the start website is randomized, and anything else will be taken as a title
		if (start.equals("x")) {
			gameExit();
		} else if (start.equals("r")) {
			backend.setStartWebsite("r");
		} else {
			backend.setStartWebsite(start);
		}
		
		//User Interface Building
		System.out.print("Now enter the title of the Wikipedia article that you want to find  |");
		System.out.print("  Enter 'r' if you want to find a random article  |");
		System.out.println("  Enter 'x' to exit.");
		String end = gameInput.nextLine();
		System.out.println(
				"************************************************************************************************************************************************************");
		//if the end var is an 'x' the game exits, if 'r' the end website is randomized, and anything else will be taken as a title
		if (end.equals("x")) {
			gameExit();
		} else if (end.equals("r")) {
			backend.setEndWebsite("r");
		} else {
			backend.setEndWebsite(end);
		}

		boolean win;//variable for win condition
		
		//do-while loop that loops until the win condition is set to true
		do {
			//User Interface Building
			System.out.println(
					"************************************************************************************************************************************************************");
			//Every turn this line is called to give the user an ingame interface and to send information to the backend to update its data
			backend.setCurrent(
					gameInterface(backend.getCurrentWebsite(), backend.getEndWebsite(), backend.displayCurrentLinked()));
			//UI Building
			System.out.println(
					"************************************************************************************************************************************************************");
			win = backend.gameOver();//after backend's data is updated, win condition is checked
		} while (win != true);
		//UI For when the user wins, Big ASCII art that says "You WON!"
		System.out.println(
				"************************************************************************************************************************************************************");
		System.out.println("Current Page Title: " + backend.getStartWebsite() + "  |  Target Page Title: "
				+ backend.getEndWebsite() + "  |  Turn: " + turn);
		System.out.println("\r\n" + "  __   __   U  ___ u   _   _                     U  ___ u  _   _     _    \r\n"
				+ "  \\ \\ / /    \\/\"_ \\/U |\"|u| |     __        __    \\/\"_ \\/ | \\ |\"|  U|\"|u  \r\n"
				+ "   \\ V /     | | | | \\| |\\| |     \\\"\\      /\"/    | | | |<|  \\| |> \\| |/  \r\n"
				+ "  U_|\"|_u.-,_| |_| |  | |_| |     /\\ \\ /\\ / /\\.-,_| |_| |U| |\\  |u  |_|   \r\n"
				+ "    |_|   \\_)-\\___/  <<\\___/     U  \\ V  V /  U\\_)-\\___/  |_| \\_|   (_)   \r\n"
				+ ".-,//|(_       \\\\   (__) )(      .-,_\\ /\\ /_,-.     \\\\    ||   \\\\,-.|||_  \r\n"
				+ " \\_) (__)     (__)      (__)      \\_)-'  '-(_/     (__)   (_\")  (_/(__)_) \r\n" + "");

		System.out.println("The minimum turns was " + backend.getShortestPath()+" to go from " + backend.getStartWebsite() + " to "
				+ backend.getEndWebsite() + ". One path was: " + backend.getShortestPathNodes() + ".");//tells the user the minimum turns needed to get to the target vs their number of turns
		System.out.println("Thank you for Playing!");
		String temp;//holds user input from console on what to do after the game
		
		//do-while loop for user input post-game, if 'x' is entered the game exits, if 'p' is entered game is reset to be played again, loop repeats until proper input is given
		do {
			System.out.println("Enter 'x' to exit and 'p' to play again.");
			temp = gameInput.next();
			if (temp.equals("x")) {
				gameExit();
			} else if (temp.equals("p")) {
				restartApplication();
			} else {
				System.out.println("Invalid Input");
			}
		} while (!temp.equals("x") && !temp.equals("p"));
		System.out.println(
				"************************************************************************************************************************************************************");

	}

	/**
	 * Body of the game, is responsible for incrementing turns, displaying the user
	 * his next nodes & other game info, and sending the information chosen by the
	 * user to the back end
	 * 
	 * @param current the title of the wikipedia page the user on, to be displayed
	 *                to the user
	 * @param target  the title of the wikipedia page the user is trying to find, to
	 *                be displayed to the user
	 * @param links   a list of titles the current page links to, to be displayed to
	 *                the user
	 * @return a String, the title of the next chosen wikipedia page, or null if the method fails
	 */
	private static String gameInterface(String current, String target, List<String> links) {
		turn++;//increments the turn the user is on
		//UI BUILDING
		System.out.println("Current Page Title: " + current + "  |  Target Page Title: " + target + "  |  Turn: " + turn
				+ " | Enter the Number of the Link You Want To Visit Next  |  Enter 'x' to Exit");
		//for loop that prints a list of the titles of the connecting links on the current wikipedia page
		for (int i = 0; i < links.size(); i++) {
			System.out.println(i+1 + ". " + links.get(i));
		}
		
		Scanner interfaceInput = new Scanner(System.in);//scanner for input in the interfrace from the console
		System.out.print("Link Number: ");
		String linkNumber;//var to hold user input
		
		//do-while loop that gains input until it is 'x' to exit the game, or a number of the website title they want to choose next
		do {
			linkNumber = interfaceInput.nextLine();
			if (linkNumber.equals("x")) {
				gameExit();
			} else {
				try {
					int temp = Integer.parseInt(linkNumber);
					return links.get(temp-1);
				} catch (NumberFormatException e) {
					System.out.println(
							"Input invalid! Enter the Number of the Link You Want To Visit Next or Enter 'x' to Exit.");
					linkNumber = interfaceInput.nextLine();
				}
			}
		} while (!linkNumber.equals("x"));
		return null;
	}

	/**
	 * Method that is responsible for exiting the game
	 */
	private static void gameExit() {
		//displays exit message and exits the game
		System.out.println("Exiting the Program... Good Bye!");
		System.exit(0);
	}

	/**
	 * Getter method for the turn variable
	 * 
	 * @return the current turn the player is on as an int
	 */
	public int getTurn() {
		return this.turn;//returns the turn variable
	}
	
	/**
	 * Method to restart the program fo
	 * @author Veger on Stack Overflow
	 * https://stackoverflow.com/questions/4159802/how-can-i-restart-a-java-application#:~:text=Strictly%20speaking%2C%20a%20Java%20program,no%20action%20can%20be%20taken.
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private static void restartApplication() throws URISyntaxException, IOException
	{
		turn=0;
	  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	  final File currentJar = new File(Frontend.class.getProtectionDomain().getCodeSource().getLocation().toURI());

	  /* is it a jar file? */
	  if(!currentJar.getName().endsWith(".jar"))
	    return;

	  /* Build command: java -jar application.jar */
	  final ArrayList<String> command = new ArrayList<String>();
	  command.add(javaBin);
	  command.add("-jar");
	  command.add(currentJar.getPath());

	  final ProcessBuilder builder = new ProcessBuilder(command);
	  builder.start();
	  System.exit(0);
	}
}
