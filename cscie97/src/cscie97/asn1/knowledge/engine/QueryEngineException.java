package cscie97.asn1.knowledge.engine;

public class QueryEngineException extends Exception{
	/**
	 * The QueryEngineException is thrown when there is a problem 
	 * importing the query file, or one of the queries is malformed
	 * @param message
	 */
	public QueryEngineException(String message){
		super(message);
	}
}
