/*	
 * 	File:				Main.java
 * 	Associated Files:	Deck.java, OldMaid.java
 * 	Packages Needed:	
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        Michael Ngo
 * 
 * 	Purpose:			Run a simple Old Maid game in console
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/*
 * Class:				Main
 * Purpose:				Run the code
 * Methods:				main
 */
public class Main {
	
	/*
	 * Function:			main 
	 * Params: 				commandLineArguments(String[]) {Not used}
	 * Purpose:				Handle overhead components to structure Old Maid game
	 * Returns: 			
	 */
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to Old Maid! How many players will you have? ");
        int numPlayers = scanner.nextInt();
        OldMaid oldMaidGame = new OldMaid(numPlayers);
        int winner = oldMaidGame.PlayGame() + 1;
        System.out.println("Sorry, player #" + (winner + 1) + ", you lost!");
    }
}
