package com.example.memorygame;

public class Equation {

	//Variables that make up Equation objects
	//expressionA sign expressionB = answer
	private int equationID = 0;
	private int expressionA;
	private String sign;
	private int expressionB;
	private int answer;
	private String equation;
	
	//Equation constructor; forces entire expression to be set; auto-increments ID
	public Equation(int exprA, String sign, int exprB){
		equationID++;
		expressionA = exprA;
		expressionB = exprB;
		this.sign = sign;
		calculate();
	}
	
	//Returns the equation ID
	public int getID(){
		return equationID;
	}
	
	//Returns the entire equation
	public String getEquation(){		
		equation = expressionA + " " + sign + " " + expressionB + " = " + answer;
		return equation;
	}
	
	//Returns just the expression
	public String getExpression(){
		return (expressionA + " " + sign + " " + expressionB);
	}
	
	//Returns just the answer as a string
	public String getAnswer(){
		return (answer + "");
	}
	
	//Returns answer as an int
	public int getAnswerAsInt(){
		return answer;
	}
	
	//Calculates and sets the answer using the expression
	public void calculate(){
		if(sign.equals("+"))
			answer = expressionA + expressionB; 
		else if(sign.equals("-"))
			answer = expressionA - expressionB;
	}
	
}
