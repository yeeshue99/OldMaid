/*	
 * 	File:				Deck.java
 * 	Associated Files:	Main.java, OldMaid.java
 * 	Packages Needed:	java.util.ArrayList, java.util.Collections
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        John Lukowski
 * 
 * 	Purpose:			Setup card structure to be used in card games
 */

import java.util.ArrayList;
import java.util.Collections;



/*
 * Class:				Deck
 * Purpose:				Handle all card operations
 * Methods:				MakeDeck, DealCards
 */
public class Deck {

	
	// Different combinations for cards
	String[] suits = {"Clubs", "Hearts", "Spades", "Diamonds"};
	String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	
	public Deck() {
		MakeDeck();
	}
	
	// Container to hold all cards
	public ArrayList<String> deck = new ArrayList<String>();
	
	/*	
	 * 	Function:				MakeDeck
	 * 	Params:				
	 * 	Purpose:				Initialize central deck to hold all cards but the Queen of Hearts
	 * 	Returns:				void
	 */
	public void MakeDeck() {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if(! (values[j] == "Q" && suits[i] == "Hearts")) {
					deck.add(values[j] + " of " + suits[i]);
				}
			}
		}
		
		Collections.shuffle(deck);
	}

	/*	
	 * 	Function:				DealCards
	 * 	Params:					Number of hands(int)
	 * 	Purpose:				Evenly split every card from the center deck to each hand
	 * 	Returns:				Each hand
	 */
	public ArrayList<ArrayList<String>> DealCards(int numPlayers) {
		ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
		if (numPlayers == 1) {
			numPlayers = 2;
		}
		else {
			int player = 0;
			while (!deck.isEmpty()) {
				hands.get(player).add(deck.get(0));
				deck.remove(0);
				player++;
				if(player >= numPlayers) {
					player = 0;
				}
			}
		}
		
		
		return hands;
	}
	
	/*	
	 * 	Function:				RemovePairs
	 * 	Params:					player's hand(ArrayList<String>)
	 * 	Purpose:				Removes every pair of cards from the hand
	 * 	Returns:				New hand with all pairs removed
	 */
	public ArrayList<String> RemovePairs(ArrayList<String> hand) {
		
		
		return hand;
		
	}
}


