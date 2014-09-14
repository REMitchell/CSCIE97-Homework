package cscie97.asn1.knowledge.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author ryan
 *
 */
public class KnowledgeGraph {

	//Private association for maintaining the active set of Nodes (i.e. Subjects and/or Objects). 
	//Map key is the node identifier and value is the associated Node.  Node identifiers are case insensitive
	private Map<String, Node> nodeMap;
	//Private association for maintaining the active set of Predicates. Map key is the predicate 
	//identifier and value is the associated Predicate.  Predicate identifiers are case insensitive.   
	private Map<String, Predicate> predicateMap;
	//Private association for maintaining the active set of Triples. Map key is the Triple identifier 
	//and value is the associated Triple.  
	private Map<String, Triple> tripleMap;
	//Private association for maintaining a fast query lookup map.  Map key is the query 
	private Map<String, Set<Triple>> queryMapSet;
	
	private static KnowledgeGraph instance;
	
	/**
	 * Disallowing outside instantations
	 */
	private KnowledgeGraph(){
		nodeMap = new HashMap<String, Node>();
		predicateMap = new HashMap<String, Predicate>();
		tripleMap = new HashMap<String, Triple>();
		queryMapSet = new HashMap<String, Set<Triple>>();
	}
	/**This method returns a reference to the single static 
	 * instance of the  KnowledgeGraph using the singleton pattern. 
	**/
	public static KnowledgeGraph getInstance(){
		if(instance == null){
			instance = new KnowledgeGraph();
		}
		return instance;
	}
	
	/**
	 * Public method for adding a list of Triples to the KnowledgeGraph.  The following 
	 * associations must be updated: nodeMap, tripleMap, queryMapSet, predicateMap to 
	 * reflect the added Triple.  There should be one Triple instance per unique Subject, 
	 * Predicate, Object combination, so that Triples are not duplicated.  
	 * **/
	public void importTriples(List<Triple> tripleList){
		for(Triple triple : tripleList){
			List<String> possibleQueries = getPossibleQueries(triple);
			for(String possibleQuery : possibleQueries){
				//If this "possibleQuery" does not exist yet, add it
				if(!queryMapSet.containsKey(possibleQuery)){
					queryMapSet.put(possibleQuery, new HashSet<Triple>());
				}
				queryMapSet.get(possibleQuery).add(triple);
			}
		}
	}
	
	/**
	 * Attempt to located the set of triples corresponding to the query string
	 * If not found, return an empty set.
	 * @param query
	 * @return
	 */
	public Set<Triple> executeQuery(String query){
		if(queryMapSet.containsKey(query)){
			return queryMapSet.get(query);
		}else{
			return new HashSet<Triple>();
		}
	}
	
	public Node getNode(String identifier){
		if(nodeMap.containsKey(identifier)){
			return nodeMap.get(identifier);
		}
		nodeMap.put(identifier, new Node(identifier));
		return nodeMap.get(identifier);
	}
	public Predicate getPredicate(String identifier){
		if(predicateMap.containsKey(identifier)){
			return predicateMap.get(identifier);
		}
		predicateMap.put(identifier, new Predicate(identifier));
		return predicateMap.get(identifier);
	}
	
	public Triple getTriple(Node subject, Predicate predicate, Node object){
		Triple triple = new Triple(subject, predicate, object);
		if(tripleMap.containsKey(triple.getIdentifier())){
			return tripleMap.get(triple.getIdentifier());
		}
		else{
			tripleMap.put(triple.getIdentifier(), triple);
			return triple;
		}
	}
	
	/**
	 * Creates a list of all 8 possible queries that will return this Triple as a 
	 * result, returns them as a List of Strings for storing in the queryMapSet
	 * @param triple
	 * @return
	 */
	private List<String> getPossibleQueries(Triple triple){
		List<String> possibleQueries = new ArrayList<String>();
		possibleQueries.add(triple.getIdentifier());
		possibleQueries.add(triple.getSubject().getIdentifier()+" "+triple.getPredicate().getIdentifier()+" ?");
		possibleQueries.add(triple.getSubject().getIdentifier()+" ? ?");
		possibleQueries.add("? ? ?");
		possibleQueries.add("? ? "+triple.getObject().getIdentifier());
		possibleQueries.add("? "+triple.getPredicate().getIdentifier()+" "+triple.getObject().getIdentifier());
		possibleQueries.add("? "+triple.getPredicate().getIdentifier()	+" ?");
		possibleQueries.add(triple.getSubject().getIdentifier()+" ? "+triple.getObject().getIdentifier());
		
		return possibleQueries;
		}
}
