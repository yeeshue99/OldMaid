import java.util.ArrayList;

/*	
 * 	File:				Deck.java
 * 	Associated Files:	Main.java, OldMaid.java
 * 	Packages Needed:	java.util.ArrayList, java.util.Collections
 * 	Author:            	Michael Ngo (https://github.com/yeeshue99)
 * 	Date Modified:      8/12/2020 by Michael Ngo
 * 	Modified By:        John Lukowski
 * 
 * 	Purpose:			Underlying structure for Old Maid card game
 */


public class OldMaid {
	
	int numPlayers;
	Deck deck;
	
	public OldMaid(int numPlayers) {
		this.numPlayers = numPlayers;
		deck = new Deck();
	}
	
	
	public int PlayGame(int numPlayers) {
		
		ArrayList<ArrayList<String>> allHands = deck.DealCards(numPlayers);
		
		
		int playerWon = -1;
		return playerWon;
	}
}
