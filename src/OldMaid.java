/*	
 * 	File:				OldMaid.java
 * 	Associated Files:	Main.java, Deck.java
 * 	Packages Needed:	java.util.ArrayList, java.util.HashMap, java.util.Scanner
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        Michael Ngo
 * 
 * 	Purpose:			Underlying structure for Old Maid card game
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Class:				OldMaid
 * Purpose:				Handles Old Maid engine and game
 * Methods:				PlayGame, NextPlayer, NextPlayerChoice
 */
public class OldMaid {
	
	
	int numPlayers;
	Deck deck;
	ArrayList<ArrayList<String>> allHands;
	HashMap<Integer, Boolean> playersDone;
	Scanner sc;
	
	/*
	 * Function:			Initialize
	 * Params: 				Number of players(int)
	 * Purpose:				Initializes Old Maid engine
	 * Returns: 			
	 */
	public OldMaid(int numPlayers) {
		this.numPlayers = numPlayers;
		deck = new Deck();
		System.out.println("Dealing the deck evenly to every player...");
		allHands = deck.DealCards(numPlayers);
		playersDone = new HashMap<Integer, Boolean>();
		for(int i = 0; i < numPlayers; i++) {
			System.out.printf("Okay, Player #%d, let's see if you had any pairs...%n", (i + 1));
            deck.RemovePairs(allHands.get(i));
            playersDone.put(i, false);
		}
	}
	
	/*
	 * Function:			PlayGame
	 * Params: 				Java command line input(Scanner)
	 * Purpose:				Run the game loop and communicate with user
	 * Returns: 			Player who lost(int)
	 */
	public int PlayGame(Scanner sc) {
		System.out.println("Welcome to the game of Old Maid!");
		this.sc = sc;
		
		int chosenCard = -1;
		int player = 0;
		int nextPlayer = 1;
		while(true) {
			System.out.println("======================================");
            System.out.printf("Player #%d, these are your cards:%n", (player + 1));
            deck.DisplayCards(allHands.get(player));

			chosenCard = GetPlayerChoice(player, nextPlayer);
			System.out.println("You got the " + allHands.get(nextPlayer).get(chosenCard));
			allHands.get(player).add(allHands.get(nextPlayer).get(chosenCard));
			allHands.get(nextPlayer).remove(chosenCard);

			System.out.printf("Okay, Player #%d, let's see if you had any pairs...%n", (player + 1));
			playersDone.put(nextPlayer, deck.RemovePairs(allHands.get(nextPlayer)));
			playersDone.put(player, deck.RemovePairs(allHands.get(player)));
			
			player = NextPlayer(player);
            
            nextPlayer = NextPlayer(player);

            if(player == nextPlayer) {
            	break;
            }
		}
		int playerLost = player;
		return playerLost;
	}
	
	/*
	 * Function:			NextPlayer
	 * Params: 				Current player position(int)
	 * Purpose:				Change player index to the next valid player
	 * Returns: 			Index of the next valid player
	 */
	private int NextPlayer(int player) {
		player++;
		if (player >= numPlayers) {
			player = 0;
		}
        while(playersDone.get(player)) {
        	player++;
			if (player >= numPlayers) {
				player = 0;
			}
        }
        return player;
	}

	/*
	 * Function:			GetPlayerChoice 
	 * Params: 				Current player position(int)
	 * Purpose:				Validate user input
	 * Returns: 			Index of the chosen card
	 */
	private int GetPlayerChoice(int player, int nextPlayer) {
		System.out.println("Okay, player #" + (player + 1) +", Player #" + (nextPlayer + 1) + " has " + allHands.get(nextPlayer).size() + " cards.");
		System.out.println("Which card will you take? ");
		System.out.println("Choose a number between 1 and " + allHands.get(nextPlayer).size() + ": ");
		int chosenCard = -1;

		chosenCard = sc.nextInt();
		//sc.nextLine();
		
		while(!(chosenCard >= 1 && chosenCard <= allHands.get(nextPlayer).size())) {
			System.out.println("Invalid card number. Please enter integer between 1 and " + allHands.get(nextPlayer).size() + ": ");
			chosenCard = sc.nextInt();
		}
		return chosenCard - 1;
	}



}
