package mainPackage;

import java.util.ArrayList;
import java.util.Random;

public class AmericanAuction {
	Integer numofPlayers;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Card> cardsOnAuction = new ArrayList<Card>();
	Card bidItem;
	Character[] suit = { 'H', 'S', 'C' };

	public AmericanAuction(int numofPlayers) {
		this.numofPlayers = numofPlayers;
		bidItem = null;

		// Diamond Cards
		for (int i = 0; i < 13; i++) {
			cardsOnAuction.add(new Card(i + 1, 'D'));
		}

		// Initialise Player with arraylist of cards
		for (int i = 0; i < numofPlayers; i++) {
			ArrayList<Card> playerCards = new ArrayList<Card>();
			for (int j = 0; j < 13; i++) {
				playerCards.add(new Card(j + 1, suit[i]));
			}
			players.add(new Player(playerCards));
		}

	}

	public Card getCardOnAuction() {
		return bidItem;
	}

	private Card nextCard() {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(cardsOnAuction.size());
		bidItem = cardsOnAuction.get(index);
		cardsOnAuction.remove(index);
		return bidItem;
	}

	public ArrayList<Integer> getPlayerBids() {
		ArrayList<Integer> playerBids = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++)
			playerBids.add(players.get(i).getMyBid().getValue());

		return playerBids;
	}

	public ArrayList<Float> getPlayerScores() {
		ArrayList<Float> playerScores = new ArrayList<Float>();
		for (int i = 0; i < playerScores.size(); i++)
			playerScores.add(players.get(i).getScore());

		return playerScores;
	}

	private void updateScore(ArrayList<Integer> winnerIndex) {
		for (int i = 0; i < winnerIndex.size(); i++) {
			players.get(winnerIndex.get(i)).updateScore(
					(getCardOnAuction().getValue() / winnerIndex.size()));
		}
	}

	public void roundWinner() {
		int maxBid = players.get(0).getMyBid().getValue();
		ArrayList<Integer> winnerIndex = new ArrayList<Integer>();
		// Winner of a round
		
		for (int i = 1; i < players.size(); i++) {
			int playerBid = players.get(i).getMyBid().getValue();
			if (playerBid > maxBid) {
				winnerIndex.clear();
				maxBid = playerBid;
				winnerIndex.add(i);
			}
			if (playerBid == maxBid) {
				winnerIndex.add(i);
			}
		}
	}

	public void playRound() {
		nextCard();

		// Update winner Score
		roundWinner();
	}

	public ArrayList<Integer> gameWinner() {
		float maxScore = players.get(0).getScore();
		ArrayList<Integer> gameWinnerIndex = new ArrayList<Integer>();
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

		for (int j = 0; j < 13; j++) {
			playRound();
		}
		return gameWinner();
	}

}
