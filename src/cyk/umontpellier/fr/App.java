package cyk.umontpellier.fr;

import java.io.FileNotFoundException;

public class App 
{
    public static void main( String[] args )
    {
    	RuleBase rules = null;
    	try {
			rules = new RuleBase("D:\\OneDrive\\Documentos\\DECOL\\eclipse-workspace\\TALN_CYK\\src\\rules.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("***** RÈGLES *****");
    	System.out.println(rules.toString());
    	
    	System.out.println("***** PHRASE 1 *****");
    	String sentence = "le bien qu' il fait , il le fait bien .";
    	System.out.println(sentence);
    	System.out.println(rules.validateSequence(sentence));
    	
    	System.out.println("***** PHRASE 2 *****");
    	sentence = "le fait est que j' ai peu de biens .";
    	System.out.println(sentence);
    	System.out.println(rules.validateSequence(sentence));
    	
    	System.out.println("***** PHRASE 3 *****");
    	sentence = "j' ai bien du mal à faire ce que les autres font bien .";
    	System.out.println(sentence);
    	System.out.println(rules.validateSequence(sentence));
    	
    	System.out.println("***** PHRASE 5 *****");
    	sentence = "bien qu' il ait fait du bien , il ne l' a pas bien fait .";
    	System.out.println(sentence);
    	System.out.println(rules.validateSequence(sentence));
    	
    }
}