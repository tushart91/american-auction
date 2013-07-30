package mainPackage;

import java.util.*;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(ArrayList CardList) {
		this.MyCards = new ArrayList();
		Collections.copy(this.MyCards, CardList);
		this.sortMyCards();
		this.score = 0.0;
		this.MyBid = new Card();
	}
	
	public Card nextBid() {
		return this.randomCard();
	}

}
