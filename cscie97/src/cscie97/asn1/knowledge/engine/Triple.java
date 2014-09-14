package cscie97.asn1.knowledge.engine;

public class Triple {

	private Node subject;
	private Predicate predicate;
	private Node object;
	
	public Triple(String identifier) {
		String[] tripleArray = identifier.split(" ");
		this.subject = new Node(tripleArray[0]);
		this.predicate = new Predicate(tripleArray[1]);
		this.object = new Node(tripleArray[2]);
		
	}
	public Triple(Node subject, Predicate predicate, Node object){
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}
	
	public String getIdentifier(){
		return subject.getIdentifier()+" "+predicate.getIdentifier()+" "+object.getIdentifier();
	}
	
	public Node getSubject(){
		return subject;
	}
	public Predicate getPredicate(){
		return predicate;
	}
	public Node getObject(){
		return object;
	}
}
