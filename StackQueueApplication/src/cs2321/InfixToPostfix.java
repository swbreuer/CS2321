package cs2321;

public class InfixToPostfix {
	/* Convert an infix expression and to a postfix expression
	 * infix expression : operator is between operands. Ex. 3 + 5
	 * postfix Expression: operator is after the operands. Ex. 3 5 +
	 * 
	 * The infixExp expression includes the following
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      and parenthesis: ( , )
	 *      
	 *      For easy parsing the expression, there is a space between operands and operators, parenthesis. 
	 *  	Ex: "1 * ( 3 + 5 )"
	 *      Notice there is no space before the first operand and after the last operand/parentheses. 
	 *  
	 * The postExp includes the following 
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      
	 *      For easy parsing the expression, there should have a space between operands and operators.
	 *      Ex: "1 3 5 + *"
	 *      Notice there is space before the first operand and last operator. 
	 *      Notice that postExp does not have parenthesis. 
	 */
	/**
	 * takes in an input expression in infix notation with spaces and converts it to a postfix expression with spaces
	 * breaks up input into tokens then checks if each token is a:
	 * - number
	 * - open parentheses 
	 * - close parentheses
	 * - plus or minus
	 * - multiply or divide
	 * and preserves order of operations with preference to the leftmost operator for equal order operators
	 * 
	 * @param infixExp with spaces between tokens
	 * @return posFfix expression with spaces between tokens
	 */
	public static String convert(String infixExp) {
		//TODO : complete this function with the help of Stack
		//Hint: you can use the string.split(" ") to return an array of tokens in infixExp. 
		String[] divided = infixExp.split(" ");
		String output = "";
		DLLStack<String> stack = new DLLStack<String>();
		
		for(String token: divided) {
			
			//attempts to parse the token to a number then put it on the stack
			try {
				output = output + Integer.parseInt(token) + " ";
				continue;
			} 
			catch (NumberFormatException e) {}
			
			//checks open parentheses
			if(token.equals("(")) {
				
				stack.push(token);
				continue;
			}
			
			//performs close parentheses operation
			else if(token.equals(")")) {
				
				while(!stack.top().equals("(")) {
					output = output + stack.pop() + " ";
				}
				stack.pop();
				continue;
			}
			//end close parentheses operation
			
			//begins operation checking
			else {
				//pushes any operator if stack is empty
				if(stack.isEmpty()) {
					stack.push(token);
				}
				
				//checks for addition and subtraction, lowest order operators
				else if(token.equals("+") | token.equals("-")) {
					//puts any operators onto the output
					while(!stack.isEmpty()){
						if(stack.top().equals("(")) {
							break;
						}
						output = output + stack.pop() + " ";
					}
					//puts operator on stack
					stack.push(token);
					continue;
				}
				
				//checks for multiplication and division, highest order operators
				else{
					//puts any equal order operators on the output
					while(!stack.isEmpty()){
						if(stack.top().equals("(") | stack.top().equals("+") | stack.top().equals("-")) {
							break;
						}
						output = output + stack.pop() + " ";
					}
					//puts operator on stack
					stack.push(token);
				}
			}
		}
		
		//puts all operators left on the stack in the output
		while(!stack.isEmpty()) {
			String token = stack.pop();
			
			if(stack.isEmpty()) {
				output = output + token;
				continue;
			}
			
			output = output + token + " ";
		}
		
		return output;
	}	
}
