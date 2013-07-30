package mainPackage;

import java.util.*;
import java.util.Random;

public class Player {
	
	Card myBid;
	float score;
	ArrayList<Card> myCards;
	
	public Player(ArrayList<Card> CardList) {
		this.myCards = new ArrayList<Card>(CardList);
		this.score = 0.0f;
		this.myBid = new Card();
	}
	
	public Card highestCard() {
		return (Card) this.myCards.get(this.myCards.size()-1);
	}
	
	public Card lowestCard() {
		return (Card) this.myCards.get(0);
	}
	
	public Card randomCard() {
		Random r = new Random();
		return (Card) this.myCards.get(r.nextInt(this.myCards.size()));
	}
	
	public void updateScore(float val) {
		this.score += val;
	}
	
	public float getScore() {
		return this.score;
	}
	
	public Card getMyBid() {
		return this.myBid;
	}
	
	public void setMyBid(Card c) {
		this.myBid = c; 
	}
	
	public void removeFromList(Card next) {
		for (int i = 0; i < this.myCards.size(); i++)
			if (this.myCards.get(i).getValue() == next.getValue()) {
				this.myCards.remove(i);
				break;
			}	
	}
}
