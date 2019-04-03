package cyk.umontpellier.fr;

public class TerminalRule {

	private String A;
	private String J;
	
	public TerminalRule(String a, String j) {
		super();
		A = a;
		J = j;
	}
	
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getJ() {
		return J;
	}
	public void setJ(String j) {
		J = j;
	}
	
	public String toString() {
		return A + " -> " + J;
	}
}
