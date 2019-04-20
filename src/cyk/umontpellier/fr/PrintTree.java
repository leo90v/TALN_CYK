package cyk.umontpellier.fr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrintTree {
	
	public void print(String content) throws IOException, InterruptedException {

    	String tex = 
				"\\documentclass[a4paper]{article}\n" + 
				"\\pagestyle{empty}\n" + 
				"\\usepackage{pdflscape}\n" + 
				"\\usepackage[utf8]{inputenc}\n" + 
				"\\usepackage[T1]{fontenc}\n" + 
				"\\usepackage[linguistics]{forest}\n" + 
				"\\usepackage{geometry}\n" + 
				"\\usepackage{pdflscape}\n" + 
				"\\textwidth = 800pt\n" +
    			"\\begin{document}\n" + 
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
        p.waitFor();
	}
	
}
