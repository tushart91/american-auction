package mainPackage;

public class Card {
	
	char suit;
	int faceValue;
	
	public Card(){
		this.suit = '\0';
		this.faceValue = 0;
	}
	
	public Card(int faceValue, char suit){
		this.faceValue = faceValue;
		this.suit = suit;
	}
	
	public char getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.faceValue;
	}
	
	public int compareToIgnoreSuit(Card card) {
	    if (this.faceValue < card.faceValue)
	    	return -1;
	    else if (this.faceValue > card.faceValue)
	    	return 1;
	    else
	    	return 0;
	}
	
	public String toString() {
	    String card = "";
	    switch (this.faceValue) {
	      case 0:
	        card += "Joker of ";
	        break; 
	      case 1:
	        card += "Ace of ";
	        break; 
	      case 11:
	        card += "Jack of "; 
	        break;
	      case 12:
	        card += "Queen of "; 
	        break;
	      case 13:
	        card += "King of "; 
	        break;
	      default:
	        card += this.faceValue + " of ";  //all digits
	        break;
	    }
	    switch (this.suit) {
	      	case 'H':
	      	case 'h':
	      		card += "Hearts";
	      		break;
	      	case 'D':
	      	case 'd':
	      		card += "Diamonds";
	      		break;
	      	case 'C':
	      	case 'c':
	      		card += "Clubs"; 
	      		break;
	      	case 'S':
	      	case 's':
	      		card += "Spades";
	      		break;
	      	default:
	      		//add nothing (such as to "Joker")
	      		break;
	    }
	  	return card;
	}
}

