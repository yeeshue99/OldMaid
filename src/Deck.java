/*	
 * 	File:				Deck.java
 * 	Associated Files:	Main.java, OldMaid.java, Card.java
 * 	Packages Needed:	java.util.ArrayList, java.util.Collections, java.util.Comparator;
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        Michael Ngo
 * 
 * 	Purpose:			Setup card structure to be used in card games
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Class:				Deck
 * Purpose:				Handle all card operations
 * Methods:				MakeDeck, DealCards, RemovePairs, DisplayCards,
 * 							dblDigitSort
 */
public class Deck {

	// Different combinations for cards
	static final String[] suits = { "Clubs", "Hearts", "Spades", "Diamonds" };
	static String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	// Container to hold all cards
	public static ArrayList<Card> deck = new ArrayList<Card>();

	/*
	 * Function:			MakeDeck 
	 * Params: 
	 * Purpose:				Initialize central deck to hold all cards but the Queen of Hearts
	 * Returns: 			
	 */
	public static void MakeDeck() {
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (!(values[j] == "Q" && suits[i] == "Hearts")) {
					String label = values[j] + " of " + suits[i];
					Card temp = new Card(label, Card.CardEquivalent(values[j]), Card.SuitToChar(suits[i]));
					deck.add(temp);
				}
			}
		}
		Collections.shuffle(deck);
	}

	/*
	 * Function:			DealCards 
	 * Params:				Number of hands(int)
	 * Purpose: 			Evenly split every card from the center deck to each hand 
	 * Returns:				List of all hands in game(ArrayList<ArrayList<Card>>)
	 */
	public static ArrayList<ArrayList<Card>> DealCards(int numPlayers) {
		if (numPlayers <= 1) {
			numPlayers = 2;
			System.out.println("There has to be at least two players. I assume that's what you meant!");
		}
		
		//2D ArrayList. Each element is a list of cards, and represents all of the hands in the game
		ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
		
		//Fill hands with empty ArrayList to be referenecd
		for (int i = 0; i < numPlayers; i++) {
			hands.add(new ArrayList<Card>());
		}

		int player = 0;
		//Evenly distributes deck to every hand
		while (!deck.isEmpty()) {
			hands.get(player).add(deck.remove(0));
			player++;
			if (player >= numPlayers) {
				player = 0;
			}

		}
		return hands;
	}

	/*
	 * Function:			RemovePairs
	 * Params: 				Player's hand(ArrayList<Card>)
	 * Purpose:				Removes every pair of cards from the hand
	 * Returns: 			New hand with all pairs removed
	 */
	public static Boolean RemovePairs(ArrayList<Card> hand) {
		Collections.sort(hand, dblDigitSort);
		int i = 0;
		//Look through every card. Pseudo Sliding Window Algorithm
		while (i < hand.size() - 1) {
			//Since the hand is sorted, we can find matches by looking at the cards nearby
			if (hand.get(i).GetValue() == (hand.get(i + 1).GetValue())) {
				System.out.printf("You had a pair of %s\'s!%n", hand.get(i).GetStringValue());
				hand.remove(i);
				hand.remove(i);
				//If there is a match, remove both from deck and then decrement i by 1
				i = Math.max(0, (i - 1));
			}
			//Otherwise increment by 1 to leave loop eventually
			else {
				i++;
			}
		}
		Collections.shuffle(hand);
		//If hand is empty, return true to be put in playersDone
		if(hand.size() > 0) {
			return false;
		}
		else {
			return true;
		}
	}

	/*
	 * Function:			DisplayCards
	 * Params: 				A certain player's hand(ArrayList<Card>)
	 * Purpose:				Prints out a hand to the console to be viewed
	 * Returns: 			
	 */
	public static void DisplayCards(ArrayList<Card> hand) {
		Collections.sort(hand, dblDigitSort);
		for (Card card : hand) {
			System.out.print(card.GetLabel() + ", ");
		}
		System.out.println();
	}
	
	/*
	 * Function:			dblDigitSort
	 * Params: 				
	 * Purpose:				Sorts based off of Card values: A is low, K is high.
	 * Returns: 			Comparator of two values
	 */
	public static Comparator<Card> dblDigitSort = new Comparator<Card>()
    {
		/*
		 * Function:			compare
		 * Params: 				Two cards to compare(Card, Card)
		 * Purpose:				Calculates and converts the values to be compared
		 * Returns: 			The comparison between c1 and c2
		 */
        @Override
        public int compare(Card c1, Card c2)
        {
        	Integer val1;
        	Integer val2;
        	val1 = c1.GetValue();
        	val2 = c2.GetValue();
            return val1.compareTo(val2);
        }
    };
}
