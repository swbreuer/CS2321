package cs2321;

public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * takes off each token and either puts it onto a holding stack or performs the operation on the last two elements of the stack
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) {
		String[] split = exp.split(" ");
		DLLStack<Integer> stack = new DLLStack<Integer>();
		int output = 0;
		
		for(String element: split) {
			//performs addition
			if(element.equals("+")) {
				int arg2 = stack.pop();
				int arg1 = stack.pop();
				stack.push(arg1+arg2);
			}
			//performs subtraction
			else if( element.equals("-")){
				int arg2 = stack.pop();
				int arg1 = stack.pop();
				stack.push(arg1-arg2);
				
			}
			//performs multiplication
			else if( element.equals("*")) {
				int arg2 = stack.pop();
				int arg1 = stack.pop();
				stack.push(arg1*arg2);
				
			}
			//performs division
			else if( element.equals("/")){
				int arg2 = stack.pop();
				int arg1 = stack.pop();
				stack.push(arg1/arg2);
			
			}
			//pushes number onto stack
			else {
				stack.push(Integer.parseInt(element));
			}
		}
		//if there are no numbers and 
		if(stack.isEmpty()) {
			return 0;
		}
		
		//puts final number into the output
		output = stack.pop();
		
		//if there was a number left in the stack too many numbers were given
		if(!stack.isEmpty()) {
			throw new IllegalArgumentException("Too many numbers");
		}
		return output;
	}
				
	
}
