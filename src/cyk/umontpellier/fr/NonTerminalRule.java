package cyk.umontpellier.fr;

public class NonTerminalRule {

	private String A;
	private String B;
	private String C;
	
	public NonTerminalRule(String a, String b, String c) {
		super();
		A = a;
		B = b;
		C = c;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}
	
	public String toString() {
		return A + " -> " + B + " " + C;
	}
	
}
