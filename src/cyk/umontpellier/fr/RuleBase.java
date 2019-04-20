package cyk.umontpellier.fr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RuleBase {

	private ArrayList<TerminalRule> terminalRules;
	private ArrayList<NonTerminalRule> nonTerminalRules;
	private ArrayList<String>[][] M;
	private ArrayList<Node>[][] M2;
	private int n;
	private String[] words;
	private ArrayList<String> bigrams;
	private String tree;

	public RuleBase(String path) throws FileNotFoundException {
		terminalRules = new ArrayList<>();
		nonTerminalRules = new ArrayList<>();
		bigrams = new ArrayList<>();

		File file = new File(path);
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim();

			if (line.isEmpty() || line.charAt(0) == '#')
				continue;

			String[] parts = line.split(";");
			if (parts.length == 2)
				terminalRules.add(new TerminalRule(parts[0], parts[1]));
			else
				nonTerminalRules.add(new NonTerminalRule(parts[0], parts[1], parts[2]));
		}
		sc.close();
		calculateBigrams();
	}

	private void calculateBigrams() {
		for (TerminalRule r : terminalRules) {
			if (r.getJ().contains(" "))
				bigrams.add(r.getJ());
		}
	}

	public String toString() {
		String s = "";
		for (TerminalRule r : terminalRules)
			s = s + r.toString() + "\n";
		for (NonTerminalRule r : nonTerminalRules)
			s = s + r.toString() + "\n";
		return s;
	}

	private ArrayList<String> calculateSequences(String sequence) {
		ArrayList<String> sequences = new ArrayList<>();
		sequence = sequence.replaceAll(" ", "/");
		sequences.add(sequence);

		for (String bigram : bigrams) {
			String[] parts = bigram.split(" ");
			int size = sequences.size();
			for (int i = 0; i < size; i++) {
				if (sequences.get(i).contains(parts[0] + '/' + parts[1])) {
					sequences.add(sequences.get(i).replaceFirst(parts[0] + '/' + parts[1], bigram));
				}
			}
		}
		return sequences;
	}

	public boolean validateSequence(String sequence) {
		boolean result = false;
		
		for (String s : calculateSequences(sequence.trim())) {
			words = s.trim().split("/");
			n = words.length;

			M = new ArrayList[n][n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					M[i][j] = new ArrayList<String>();

			for (int j = 0; j < n; j++)
				for (TerminalRule r : terminalRules)
					if (words[j].equals(r.getJ()))
						if (!M[0][j].contains(r.getA()))
							M[0][j].add(r.getA());

			for (int i = 1; i < n; i++)
				for (int j = 0; j < n - i; j++)
					for (int k = 0; k < i; k++) {
						ArrayList<String> b = M[k][j];
						ArrayList<String> c = M[i - k - 1][j + k + 1];
						for (NonTerminalRule r : nonTerminalRules)
							for (String B : b)
								for (String C : c)
									if (r.getB().equals(B) && r.getC().equals(C))
										if (!M[i][j].contains(r.getA()))
											M[i][j].add(r.getA());
					}

			if (M[n - 1][0].contains("S")) {
				printMatrix();
				result = true;
			}
		}
		return result;
	}

	public void printMatrix() {
		TablePrinter table = new TablePrinter(words);
		for (int i = 0; i < n; i++) {
			String[] row = new String[n];
			for (int j = 0; j < n; j++) {
				String cell = "";
				for (String s : M[i][j])
					cell = cell + s + ",";

				if (!cell.isEmpty())
					cell = cell.substring(0, cell.length() - 1);
				row[j] = cell;
			}
			table.addRow(row);
		}
		table.print();
	}
	
	public boolean validateSequence2(String sequence) {
		boolean result = false;
		tree = "";
		
		for (String s : calculateSequences(sequence.trim())) {
			words = s.trim().split("/");
			n = words.length;

			M2 = new ArrayList[n][n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					M2[i][j] = new ArrayList<Node>();

			for (int j = 0; j < n; j++)
				for (TerminalRule r : terminalRules)
					if (words[j].equals(r.getJ()))
						if (!M2[0][j].contains(r.getA()))
							M2[0][j].add(new Node(null, null, r.getA(), r.getJ()));
			
			for (int i = 1; i < n; i++)
				for (int j = 0; j < n - i; j++)
					for (int k = 0; k < i; k++) {
						ArrayList<Node> b = M2[k][j];
						ArrayList<Node> c = M2[i - k - 1][j + k + 1];
						for (NonTerminalRule r : nonTerminalRules)
							for (Node B : b)
								for (Node C : c)
									if (B.equals(r.getB()) && C.equals(r.getC())) {
										Node node = new Node(B,C,r.getA(),null);
										if (!M2[i][j].contains(node))
											M2[i][j].add(node);
									}
					}

			if (M2[n - 1][0].contains(new Node(null, null, "S", null))) {
				printMatrix2();
				if (tree == "") {
					tree = getTree2(M2[n - 1][0].get(0));
					System.out.println(tree+"\n");
				}
					
				result = true;
			}
		}
		return result;
	}
	
	public void printMatrix2() {
		TablePrinter table = new TablePrinter(words);
		for (int i = 0; i < n; i++) {
			String[] row = new String[n];
			for (int j = 0; j < n; j++) {
				String cell = "";
				for (Node s : M2[i][j])
					cell = cell + s + ",";

				if (!cell.isEmpty())
					cell = cell.substring(0, cell.length() - 1);
				row[j] = cell;
			}
			table.addRow(row);
		}
		table.print();
	}
	
	private String getTree(Node node) {
		if (node.getLeft() == null && node.getRight() == null)
	        return node.getWord()+"_"+node.getType();
	    return "["+getTree(node.getLeft())+" "+getTree(node.getRight())+"]_"+node.getType();
	}
	
	private String getTree2(Node node) {
		if (node.getLeft() == null && node.getRight() == null)
	        return "[" + node.getType() + "\\\\" + node.getWord() + "]";
	    return "["+node.getType()+" "+getTree2(node.getLeft())+" "+getTree2(node.getRight())+"]";
	}
	
	public String getTree() {
		return tree;
	}
}
