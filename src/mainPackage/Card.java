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
	     return faceValueToString() + " of " + suitToString();
	}
	
	public String faceValueToString(){
		String value = "";
		switch (this.faceValue) {
	      	case 1:
	      		value += "Ace";
	      		break; 
	      	case 11:
	      		value += "Jack"; 
	      		break;
	      	case 12:
	      		value += "Queen"; 
	      		break;
	      	case 13:
	      		value += "King"; 
	      		break;
	      	default:
	      		value += this.faceValue;  
	      		break;
	    }
	  	return value;
	}
	
	public String suitToString(){
		String suit = "";
		switch (this.suit) {
      		case 'H':
      		case 'h':
      			suit += "Hearts";
      			break;
      		case 'D':
      		case 'd':
      			suit += "Diamonds";
      			break;
      		case 'C':
      		case 'c':
      			suit += "Clubs"; 
      			break;
      		case 'S':
      		case 's':
      			suit += "Spades";
      			break;
      		default:
      			break;
		}
		return suit;
	}
}