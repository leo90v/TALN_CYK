package cyk.umontpellier.fr;

public class Node {
	
	public Node(Node left, Node right, String type, String word) {
		super();
		this.left = left;
		this.right = right;
		this.type = type;
		this.word = word;
	}
	
	private Node left;
	private Node right;
	private String type;
	private String word;
	
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	@Override 
	public boolean equals(Object object) {
		if (object instanceof String) {
			if (this.type.equals((String) object))
				return true;
			return false;
		}
		
		if (object instanceof Node) {
			if (this.type.equals(((Node) object).getType()))
				return true;
			return false;
		}
		
		if (object instanceof TerminalRule) {
			if (this.type.equals(((TerminalRule) object).getA()))
				return true;
			return false;
		}
		
		if (object instanceof NonTerminalRule) {
			if (this.type.equals(((NonTerminalRule) object).getA()))
				return true;
			return false;
		}
			
		return false;
	}
	
	public String toString() {
		return type;
	}
}
