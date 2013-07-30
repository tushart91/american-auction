package mainPackage;

import java.util.ArrayList;
import java.util.Random;

public class AmericanAuction {
	Integer numofPlayers;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Card> cardsOnAuction = new ArrayList<Card>();
	Card auctionItem;
	Character[] suit = { 'D', 'H', 'S', 'C' };

	public AmericanAuction(int numofPlayers, char auctionSuite) {
		this.numofPlayers = numofPlayers;
		auctionItem = null;

		distributeCards(numofPlayers, auctionSuite);
	}

	private void distributeCards(int numofPlayers, char auctionSuite) {

		// Auction Cards
		for (int i = 0; i < 13; i++) {
			cardsOnAuction.add(new Card(i + 1, auctionSuite));
		}

		// Initialise Player with arraylist of cards
		for (int i = 0; i < numofPlayers; i++) {
			ArrayList<Card> playerCards = new ArrayList<Card>();
			for (int j = 0; j < 13; j++) {
				playerCards.add(new Card(j + 1, suit[i + 1]));
			}
			players.add(new Player(playerCards));
		}
	}

	private Card nextAuctionCard() {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(cardsOnAuction.size());
		auctionItem = cardsOnAuction.get(index);
		cardsOnAuction.remove(index);
		return auctionItem;
	}

	public ArrayList<Card> PlayerBids() {
		ArrayList<Card> playerBids = new ArrayList<Card>();
		for (int i = 0; i < players.size(); i++)
			playerBids.add(players.get(i).getMyBid());

		return playerBids;
	}

	public ArrayList<Float> PlayerScores() {
		ArrayList<Float> playerScores = new ArrayList<Float>();
		for (int i = 0; i < players.size(); i++)
			playerScores.add(players.get(i).getScore());

		return playerScores;
	}

	private void updateScore(ArrayList<Integer> winnerIndex) {
		for (int i = 0; i < winnerIndex.size(); i++) {
			players.get(winnerIndex.get(i)).updateScore(
					(getCardOnAuction().getValue() / winnerIndex.size()));
		}
	}

	public ArrayList<Integer> roundWinner() {
		int maxBid = players.get(0).getMyBid().getValue();
		ArrayList<Integer> winnerIndex = new ArrayList<Integer>();
		winnerIndex.add(0);
		for (int i = 1; i < players.size(); i++) {
			int playerBid = players.get(i).getMyBid().getValue();
			if (playerBid > maxBid) {
				winnerIndex.clear();
				maxBid = playerBid;
				winnerIndex.add(i);
			} else if (playerBid == maxBid) {
				winnerIndex.add(i);
			}
		}
		System.out.println("Winner Index: " + winnerIndex);
		return winnerIndex;
	}

	public void playRound() {

		nextAuctionCard();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).nextBid();
		}
		System.out.print(getCardOnAuction() + " --> ");
		System.out.println(PlayerBids());
		updateScore(roundWinner());
		System.out.println("Current scores: " + PlayerScores());
	}

	public ArrayList<Integer> playGame() {

		for (int j = 0; j < 13; j++) {
			playRound();
		}
		return gameWinner();
	}

	public ArrayList<Integer> gameWinner() {
		float maxScore = players.get(0).getScore();
		ArrayList<Integer> gameWinnerIndex = new ArrayList<Integer>();
		gameWinnerIndex.add(0);
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

	public Card getCardOnAuction() {
		return auctionItem;
	}

	public static void main(String[] args) {

		AmericanAuction game = new AmericanAuction(3, 'D');
		System.out.println(game.playGame());

	}

}
