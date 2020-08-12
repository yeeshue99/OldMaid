import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

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


public class OldMaid {
	
	int numPlayers;
	Deck deck;
	ArrayList<ArrayList<String>> allHands;
	Hashtable<Integer, Integer> numCards;
	Scanner sc = new Scanner(System.in);
	
	public OldMaid(int numPlayers) {
		this.numPlayers = numPlayers;
		deck = new Deck();
		allHands = deck.DealCards(numPlayers);
		numCards = new Hashtable<Integer, Integer>();
		for(int i = 0; i < numPlayers; i++) {
			numCards.put(i, allHands.get(i).size());
		}
	}
	
	
	public int PlayGame() {
		System.out.println("Welcome to the game of Old Maid!");
		
		int chosenCard = -1;
		int player = 0;
		int nextPlayer = 1;
		while(StillPlaying() == -1) {
			chosenCard = GetPlayerChoice(player);
			allHands.get(player).add(allHands.get(nextPlayer).get(chosenCard));
			allHands.get(nextPlayer).remove(chosenCard);
			deck.RemovePairs(allHands.get(player));
			player = nextPlayer;
			if (++nextPlayer >= numPlayers) {
				nextPlayer = 0;
			}

		}
		int playerLost = StillPlaying();	
		return playerLost;
	}
	
	/*
	 * Function:			StillPlaying 
	 * Params: 
	 * Purpose:				Check if the game is over
	 * Returns: 			-1 if the game is still going on, otherwise the player who lost
	 */
	private int StillPlaying() {
		int playersDone = 0;
		int lostPlayer = -1;
		for (int i = 0; i < numPlayers; i++) {
			numCards.put(i, allHands.get(i).size());
			if(numCards.get(i) == 0) {
				playersDone++;
			}
			else {
				lostPlayer = i;
			}
		}
		if(playersDone == numPlayers - 1) {
			return lostPlayer;
		}
		else {
			return -1;
		}
	}

	/*
	 * Function:			GetPlayerChoice 
	 * Params: 				Current player position(int)
	 * Purpose:				Validate user input
	 * Returns: 			Index of the chosen card
	 */
	private int GetPlayerChoice(int player) {
		System.out.println(player);
		System.out.println("Okay, player #" + player + 1 +", Player #" + player + 2 + " has " + allHands.get(player + 1).size() + " cards.");
		System.out.println("Which card will you take? ");
		int chosenCard = sc.nextInt();
		
		while(!(chosenCard >= 1 && chosenCard <= allHands.get(player).size())) {
			System.out.println("Invalid card number. Please enter integer between 1 and " + allHands.get(player).size() + ": ");
			chosenCard = sc.nextInt();
		}
		return chosenCard;
	}



}
