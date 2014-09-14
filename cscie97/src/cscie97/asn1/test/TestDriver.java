package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;

public class TestDriver {
	
	/**method should accept 2 parameters, an input Triple file, and an Input Query file. The main 
method will call the Importer.importFile() method, passing in the name of the provided triple file. 
After loading the input triples, the main() method will invoke the executeQuery() method passing 
in the provided query file name.
	 * @throws Exception **/
	public static void main(String[] args) throws Exception{
		if(args.length != 2){
			throw new Exception("Must enter import file and query file as parameters");
		}
		Importer importer = new Importer();
		importer.importTripleFile(args[0]);
		
		QueryEngine queryEngine = new QueryEngine();
		queryEngine.executeQueryFile(args[1]);
	}
}
