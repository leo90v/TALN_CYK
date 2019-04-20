package cyk.umontpellier.fr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrintTree {
	
	public void print(String content) throws IOException {

    	String tex = 
    			"\\documentclass[11pt]{article}\n" + 
    			"\\usepackage[utf8]{inputenc}\n" + 
    			"\n" + 
    			"\\begin{document}\n" + 
    			"\n" + 
    			"\\begin{center}\n" + 
    			" \\begin{Huge}\n" + 
    			" Delete Me !!!\n" + 
    			" \\end{Huge}\n" + 
    			"\\end{center}\n" + 
    			" \\vspace{2.5cm} \n" + 
    			" \n" + 
    			content +
    			"\n" + 
    			"\\end{document}\n" + 
    			"";
    	
        BufferedWriter writer = new BufferedWriter(new FileWriter("tree.tex"));
        writer.write(tex);
         
        writer.close();
    	
        String[] cmd = { "bash", "-c", "./test.sh tree" };
        Process p = Runtime.getRuntime().exec(cmd);
	}
	
}
