package mainPackage;

import java.util.*;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(ArrayList<Card> CardList) {
		super(CardList);
	}
	
	public Card nextBid() {
		Card next = this.randomCard();
		this.myCards.remove(next);
		this.setMyBid(next);
		return next;
	}

}
