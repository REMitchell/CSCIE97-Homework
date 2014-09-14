package cscie97.asn1.knowledge.engine;

/**
 * Public class for storing both objects and subjects 
 * (lucy, starbucks, donuts, etc.) in input strings.
 * @author ryan
 *
 */
public class Node {
	private String identifier;
	
	public Node(String identifier){
		this.identifier = identifier;
	}
	
	public String getIdentifier(){
		return identifier;
	}
}
