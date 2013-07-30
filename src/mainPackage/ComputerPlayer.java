package mainPackage;

import java.util.*;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(ArrayList<Card> CardList) {
		super(CardList);
	}
	
	public Card nextBid() {
		Card next = this.randomCard();
		this.removeFromList(next);
		this.setMyBid(next);
		return next;
	}

}
