package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class QueryEngine {

	/**
	 * Public method for executing a set of queries read from a file.
	 * Checks for valid file name. Delegates to executeQuery for 
	 * processing individual queries.  Throws QueryEngineException on error.  
	 * @throws QueryEngineException
	 */
	public void executeQueryFile(String fileName) throws QueryEngineException{
		if(fileName.endsWith(".nt") || fileName.endsWith(".txt")){
			BufferedReader br;
			try{
				br = new BufferedReader(new FileReader("../files/"+fileName));
				String queryString = br.readLine();
				while(queryString != null){
					queryString = formatQuery(queryString);
					if(queryString != null){
						executeQuery(queryString);
					}
					queryString = br.readLine();
				}
				br.close();
			}catch(IOException e){
				throw new QueryEngineException(fileName+" does not exist or cannot be read");
			}
		}else{
			throw new QueryEngineException("Filename must end with \".nt\" or \".txt\"");
		}
	}
	/**
	 * Public method for executing a single query on the knowledge graph. Checks for
	 * non null and well formed query string. Throws QueryEngineException on error.
	 * executeQuery transforms the string to lower case before querying
	 */

	public void executeQuery(String query) throws QueryEngineException{
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		Set<Triple> triples = knowledgeGraph.executeQuery(query);
		System.out.println("---------------------\nQuery "+query+" matches: ");
		for(Triple triple: triples){
			System.out.println(triple.getIdentifier());
		}
	}
	
	/**
	 * formatTriple returns a properly-formatted tripleString when possible, and
	 * null if the input cannot be formatted into a tripleString
	 * @param tripleString
	 * @return
	 * @throws QueryEngineException 
	 */
	private String formatQuery(String query) throws QueryEngineException{
		//Trim any leading and trailing whitespace
		query = query.trim();
		
		if(query.equals("")){
			return null;
		}
		//Remove any trailing periods
		if(query.endsWith(".")){
			query = query.substring(0, query.length()-1);
		}
		
		//Throw an error if the query is the wrong size
		if(query.split(" ").length != 3){
			throw new QueryEngineException("Query \""+query+"\" is malformed");
		}
		return query.toLowerCase();
	}
}
