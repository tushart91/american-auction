package mainPackage;

import java.util.*;
import java.io.*;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(ArrayList<Card> CardList) {
		super(CardList);
	}
	
	public Card nextBid() throws IOException {
		Card next = this.randomCard();
		this.removeFromMyCards(next);
		this.setMyBid(next);
		return next;
	}

}
