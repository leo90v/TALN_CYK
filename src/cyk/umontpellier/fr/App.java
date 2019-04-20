package cyk.umontpellier.fr;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class App 
{
	
	
	
    public static void main( String[] args ) throws IOException 
    {
    	RuleBase rules = null;
    	try {
			rules = new RuleBase("rules.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String content = "";
    	
     	ArrayList<String> Sentences = new ArrayList<String>();
    	Sentences.add("le bien qu' il fait , il le fait bien .");
    	Sentences.add("le fait est que j' ai peu de biens .");
    	Sentences.add("j' ai bien du mal à faire ce que les autres font bien .");
    	Sentences.add("bien qu' il ait fait du bien , il ne l' a pas bien fait .");
    	Sentences.add("bien des gens ont fait un peu de bien .");
    	Sentences.add("j' ai fait du bien que les gens ont fait .");
    	
    	System.out.println("***** RÈGLES *****");
    	System.out.println(rules.toString());
    	
    	int i=1;
    	for (String s : Sentences) {
        	System.out.println("***** PHRASE "+ i++ +" *****");
        	System.out.println(s);
        	System.out.println(rules.validateSequence(s));
        	
        	// printing the Tree as well
        	
        	//adding what we want
        	content = content + "\n\n" +
        			"\\begin{landscape}\n" + 
        			"\\begin{forest}\n" + 
        			//Stringtree
        			"\n" + 
        			"\\end{forest}\n" + 
        			"\\end{landscape}\n" + 
        			"\\clearpage\n\n"
        	;
		}
    	PrintTree pdf = new PrintTree();
    	pdf.print(content);
    	
    }
}