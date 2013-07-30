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

		distributeCardsToPlayers(numofPlayers, auctionSuite);
	}

	private void distributeCardsToPlayers(int numofPlayers, char auctionSuite) {

		// Auction Cards
		for (int i = 0; i < 13; i++) {
			cardsOnAuction.add(new Card(i + 1, auctionSuite));
		}
		
		// Initialise Player with arraylist of cards
		players.add(new HumanPlayer(SuiteCards(suit[1])));
		for (int i = 1; i < numofPlayers; i++) 
			players.add(new ComputerPlayer(SuiteCards(suit[i + 1])));
	}
	
	private static ArrayList<Card> SuiteCards(char s) {
		ArrayList<Card> playerCards = new ArrayList<Card>();
		for (int j = 0; j < 13; j++) {
			playerCards.add(new Card(j + 1, s));
		}
		return playerCards;
	}

	private Card nextAuctionCard() {

		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(cardsOnAuction.size());
		auctionItem = cardsOnAuction.get(index);
		cardsOnAuction.remove(index);
		return auctionItem;
	}

	public ArrayList<Card> PlayerBids() {
		ArrayList<Card> listOfPlayerBids = new ArrayList<Card>();
		for (int i = 0; i < players.size(); i++)
			listOfPlayerBids.add(players.get(i).getMyBid());

		return listOfPlayerBids;
	}

	public ArrayList<Float> PlayerScores() {
		ArrayList<Float> listOfPlayerScores = new ArrayList<Float>();
		for (int i = 0; i < players.size(); i++)
			listOfPlayerScores.add(players.get(i).getScore());

		return listOfPlayerScores;
	}

	private void updateScore(ArrayList<Integer> winnerIndex) {
		for (int i = 0; i < winnerIndex.size(); i++) {
			players.get(winnerIndex.get(i)).updateScore(
					((float)getCardOnAuction().getValue() / winnerIndex.size()));
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
		return winnerIndex;
	}

	public void playRound() {

		for (int i = 0; i < players.size(); i++) {
			players.get(i).nextBid();
		}
		updateScore(roundWinner());
	}

	public ArrayList<Integer> gameWinner() {
		
		float maxScore = players.get(0).getScore();
		ArrayList<Integer> indexOfGameWinners = new ArrayList<Integer>();
		indexOfGameWinners.add(0);
		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).getScore() > maxScore) {
				maxScore = players.get(i).getScore();
				indexOfGameWinners.clear();
				indexOfGameWinners.add(i);
			} else if (players.get(i).getScore() == maxScore) {
				indexOfGameWinners.add(i);
			}
		}
		return indexOfGameWinners;
	}

	public Card getCardOnAuction() {
		return auctionItem;
	}

	public static void main(String[] args) {

		AmericanAuction game = new AmericanAuction(3, 'D');
		for (int j = 0; j < 13; j++) {
			game.nextAuctionCard();
			System.out.println("Card on Auction: " + game.getCardOnAuction());
			game.playRound();
			System.out.println("Player Bids: " + game.PlayerBids());
			System.out.println("Player Scores  : " + game.PlayerScores());
			System.out.println();
		}
		ArrayList<Integer> winners = game.gameWinner();
		for (int i = 0; i < winners.size(); i++)
			System.out.println("Player " + winners.get(i) + " Wins!");

	}

}
