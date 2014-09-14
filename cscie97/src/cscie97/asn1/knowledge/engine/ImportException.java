package cscie97.asn1.knowledge.engine;

public class ImportException extends Exception{

	/**
	 * ImportException is used by the Importer to throw an exception if
	 * a filename is malformed, or there was another problem importing 
	 * the file
	 * @param message
	 */
	public ImportException(String message){
		super(message);
	}

}
