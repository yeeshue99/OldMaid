/*	
 * 	File:				Deck.java
 * 	Associated Files:	Main.java, OldMaid.java
 * 	Packages Needed:	java.util.ArrayList, java.util.Collections
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
	HashMap<Integer, Integer> numCards;
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
		allHands = deck.DealCards(numPlayers);
		numCards = new HashMap<Integer, Integer>();
		playersDone = new HashMap<Integer, Boolean>();
		for(int i = 0; i < numPlayers; i++) {
			numCards.put(i, allHands.get(i).size());
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
            System.out.println("Player #" + (player + 1));
            deck.DisplayCards(allHands.get(player));
            System.out.println("Player # " + (nextPlayer + 1));
            deck.DisplayCards(allHands.get(nextPlayer));

			chosenCard = GetPlayerChoice(player, nextPlayer);
			allHands.get(player).add(allHands.get(nextPlayer).get(chosenCard));
			allHands.get(nextPlayer).remove(chosenCard);

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
