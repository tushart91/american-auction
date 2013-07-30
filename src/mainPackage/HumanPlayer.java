package mainPackage;

import java.util.*;

public class HumanPlayer extends Player {
	
	public HumanPlayer(ArrayList<Card> CardList) {
		super(CardList);
	}
	
	public Card nextBid() {
		Card next = this.randomCard();
		this.myCards.remove(next);
		this.setMyBid(next);
		return next;
	}
}
