package mainPackage;

import java.util.ArrayList;
import java.util.Random;

public class AmericanAuction {
	Integer numofPlayers;
	ArrayList<Player> players;
	ArrayList<Card> cardsOnAuction;
	Card bidItem;

	public AmericanAuction(int numofPlayers) {
		this.numofPlayers = numofPlayers;
		bidItem = null;
		for (int i = 0; i < numofPlayers; i++) {
			players.add(new Player());
		}
		for (int i = 0; i < 13; i++) {
			cardsOnAuction.add(new Card(i + 1, 'D'));
		}
	}

	public Card getCardOnAuction() {
		return bidItem;
	}

	public Card nextCard() {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(cardsOnAuction.size());
		bidItem = cardsOnAuction.get(index);
		cardsOnAuction.remove(index);
		return bidItem;
	}
	
	public ArrayList<Integer> getPlayerBids() {
		ArrayList<Integer> playerBids;
		for (int i = 0; i < players.size(); i++) {
			playerBids.add(players.get(i).getMyBid());
		}
		return playerBids;
	}

	public void playRound() {
		
		int maxBid = players.get(0).getMyBid();
		ArrayList<Integer> winnerIndex;
		for (int i = 1; i < players.size(); i++) {
			int playerBid = players.getMyBid();
			if (playerBid > maxBid) {
				winnerIndex.clear();
				maxBid = playerBid;
				winnerIndex.add(i);
			}
			if (playerBid == maxBid) {
				winnerIndex.add(i);
			}
		}
		for (int i = 0; i < winnerIndex.size(); i++) {
			players.get(winnerIndex.get(i)).updateScore(
					(getCardOnAuction().getValue() / winnerIndex.size()));
		}
		
	}

	public ArrayList<Integer> gameWinner() {
		int maxScore = players.get(0).getScore();
		ArrayList<Integer> gameWinnerIndex = 0;
		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getScore() > maxScore) {
				maxScore = players.get(i).getScore();
				gameWinnerIndex.clear();
				gameWinnerIndex.add(i);
			} else if (players.get(i).getScore() == maxScore) {
				gameWinnerIndex.add(i);
			}
		}
		return gameWinnerIndex;
	}

	public ArrayList<Integer> playGame() {
		
		for (int i = 0; i < players.size(); i++)
			nextCard();
		
		for (int j = 0; j < 13; j++) {
			playRound();
		}
		return gameWinner();
	}

}
