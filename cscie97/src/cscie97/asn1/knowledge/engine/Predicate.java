package cscie97.asn1.knowledge.engine;

/**
 * Public class for storing predicates ("has_a," "sells," etc) in input strings
 * @author ryan
 *
 */
public class Predicate {
	private String identifier;
	
	public Predicate(String identifier){
		this.identifier = identifier;
	}
	
	public String getIdentifier(){
		return identifier;
	}
}
