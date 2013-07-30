package mainPackage;

import java.util.*;
import java.util.Random;

public class Player {

	Card MyBid;
	float score;
	ArrayList MyCards;
	
	public Player(ArrayList CardList) {
		this.MyCards = new ArrayList();
		Collections.copy(this.MyCards, CardList);
		this.score = 0.0;
		this.MyBid = new Card();
	}
	
	public Card highestCard() {
		return this.MyCards.get(this.Mycards.size()-1);
	}
	
	public Card lowestCard() {
		return this.MyCards.get(0);
	}
	
	public randomCard() {
		Random r = new Random();
		return this.MyCards.get(r.nextInt(this.MyCards.size()));
	}
	
	public void updateScore(float val) {
		this.score += val;
	}
	
	public void getScore() {
		return this.score;
	}
	
}
