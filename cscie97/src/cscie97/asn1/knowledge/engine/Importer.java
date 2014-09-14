package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Importer {

	/**
	 * Public method for importing triples from N_Triple formatted file
	 * into the KnowledgeGraph. Checks for valid input file name.  
	 * Throws ImportException on error accessing or processing the input Triple File.
	 * importTripleFile will transform all strings to lowercase before using them to form
	 * triples
	 * @throws ImportException 
	 */
	public void importTripleFile(String fileName) throws ImportException{
		System.out.println("Importing file "+fileName);
		if(fileName.endsWith(".nt") || fileName.endsWith(".txt")){
			List<Triple> tripleList = new ArrayList<Triple>();
			KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
			
			BufferedReader br;
			try{
				br = new BufferedReader(new FileReader("../files/"+fileName));
				String tripleString = br.readLine();
				while(tripleString != null){
					tripleString = formatTripleString(tripleString);
					if(tripleString != null){
						String[] tripleArray = tripleString.split(" ");
						//This will also add the Triplet, Nodes, and Predicate to their respective
						//maps in the KnowledgeGraph
						tripleList.add(knowledgeGraph.getTriple(knowledgeGraph.getNode(tripleArray[0]), 
								knowledgeGraph.getPredicate(tripleArray[1]), knowledgeGraph.getNode(tripleArray[2])));
					}
					tripleString = br.readLine();
				}
				br.close();
			}catch(IOException e){
				throw new ImportException(fileName+"does not exist or cannot be read");
			}
			System.out.println("import Triples, size: "+tripleList.size());
			//Add the Triples to the lookup map
			knowledgeGraph.importTriples(tripleList);
			
		}else{
			throw new ImportException("Filename must end with \".nt\" or \".txt\"");
		}
	}
	
	/**
	 * formatTriple returns a properly-formatted tripleString when possible. Although it will
	 * return a null String for more "harmless" errors, such as empty strings, it will
	 * throw an ImportException for more serious problems like '?' characters and incorrect
	 * numbers of words.
	 * @param tripleString
	 * @return
	 * @throws ImportException 
	 */
	private String formatTripleString(String tripleString) throws ImportException{
		if(tripleString.contains("?")){
			throw new ImportException("The reserved character \'?\' cannot be used in string \""+tripleString+"\"");
		}
		//Trim any leading and trailing whitespace
		tripleString = tripleString.trim();
		
		//Ignore empty strings (probably caused by an extra line break)
		if(tripleString.equals("")){
			return null;
		}
		//Remove any trailing periods
		if(tripleString.endsWith(".")){
			tripleString = tripleString.substring(0, tripleString.length()-1);
		}	
		
		if(tripleString.split(" ").length != 3){
			throw new ImportException("Input string \""+tripleString+"\" must have three sections");
		}
		//Transform all to lowercase for consistent comparisons
		return tripleString.toLowerCase();
	}

}
