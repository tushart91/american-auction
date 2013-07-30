package mainPackage;

import java.util.*;
import java.io.*;

public class HumanPlayer extends Player {
	
	public HumanPlayer(ArrayList<Card> CardList) {
		super(CardList);
	}
	
	public Card nextBid() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Your Choice: ");
		Card next = new Card(Integer.parseInt(br.readLine()), this.myCards.get(0).getSuit());
		this.removeFromList(next);
		this.setMyBid(next);
		return next;
	}
	
	public void displayCards() {
		System.out.print(this.myCards.get(0).getSuit() + ": ");
		System.out.println(this.myCards);
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(2, 'h'));
		cards.add(new Card(3, 'h'));
		cards.add(new Card(5, 'h'));
		cards.add(new Card(7, 'h'));
		HumanPlayer h = new HumanPlayer(cards);
		h.displayCards();
		System.out.println(h.nextBid());
		h.displayCards();
	}
}
