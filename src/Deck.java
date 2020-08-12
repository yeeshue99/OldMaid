/*	
 * 	File:				Deck.java
 * 	Associated Files:	Main.java, OldMaid.java
 * 	Packages Needed:	java.util.ArrayList, java.util.Collections
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        Michael Ngo
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
	String[] suits = { "Clubs", "Hearts", "Spades", "Diamonds" };
	String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	public Deck() {
		MakeDeck();
	}

	// Container to hold all cards
	public ArrayList<String> deck = new ArrayList<String>();

	/*
	 * Function:			MakeDeck 
	 * Params: 
	 * Purpose:				Initialize central deck to hold all cards but the Queen of Hearts
	 * Returns: 			
	 */
	public void MakeDeck() {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (!(values[j] == "Q" && suits[i] == "Hearts")) {
					deck.add(values[j] + " of " + suits[i]);
				}
			}
		}

		Collections.shuffle(deck);
	}

	/*
	 * Function:			DealCards 
	 * Params:				Number of hands(int)
	 * Purpose: 			Evenly split every card from the center deck to each hand 
	 * Returns:				Each hand
	 */
	public ArrayList<ArrayList<String>> DealCards(int numPlayers) {
		ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
		if (numPlayers == 1) {
			numPlayers = 2;
		}
		
		for (int i = 0; i < numPlayers; i++) {
			hands.add(new ArrayList<String>());
		}

		int player = 0;
		while (!deck.isEmpty()) {
			hands.get(player).add(deck.get(0));
			deck.remove(0);
			player++;
			if (player >= numPlayers) {
				player = 0;
			}

		}

		for (int i = 0; i < numPlayers; i++) {
			Collections.sort(hands.get(i));
		}
		return hands;
	}

	/*
	 * Function:			RemovePairs
	 * Params: 				player's hand(ArrayList<String>)
	 * Purpose:				Removes every pair of cards from the hand
	 * Returns: 			New hand with all pairs removed
	 */
	public ArrayList<String> RemovePairs(ArrayList<String> hand) {
		Collections.sort(hand);
		int i = 0;
		while (i < hand.size() - 1) {
			// Check each sequence of two cards
			if (hand.get(i).equals(hand.get(i + 1))) {
				// Remove card at index i twice - effectively removes i and i+1
				hand.remove(i);
				hand.remove(i);

				// Move i back by one, so we keep our position in the list consistent,
				// since we just removed two elements,
				// then make sure i stays positive.
				i = Math.max(0, (i - 1));
			} else {
				i++;
			}
		}
		return hand;

	}

	/*
	 * Function:			DisplayCards
	 * Params: 				player's hand(ArrayList<String>)
	 * Purpose:				Prints out a hand to the console to be viewed
	 * Returns: 			
	 */
	public void DisplayCards(ArrayList<String> hand) {
		for (int i = 0; i < hand.size(); i++) {
			System.out.print(hand.get(i) + ", ");
		}
		System.out.println();
	}
}
