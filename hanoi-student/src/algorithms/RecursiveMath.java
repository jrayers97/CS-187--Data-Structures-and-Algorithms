package algorithms;

/**
 * A class that implements some basic mathematical functions using recursion
 *
 * @author jcollard
 */
public class RecursiveMath {

	/**
	 * Returns {@code true} if {@code val} is even and {@code false} otherwise.
	 * A number is even if it is zero or if its predecessor is an odd number. A
	 * negative number is even if its negation is even.
	 *
	 * @param val
	 * @return {@code true} if {@code val} is even and {@code false} otherwise.
	 */
	public boolean isEven(int val) {
    // Without recursion this could be: return val % 2 == 0;
		boolean even = false;
		if(val == 0){
			even = true;
		}
		if(val < 0){
			val = val*-1;
		}
		if(val > 0){
			if(isOdd(val-1) == true){
			even = true;
			}
		}
		return even;
	}

	/**
	 * Returns {@code true} if {@code val} is odd and {@code false} otherwise.
	 * Zero is not odd, but one is odd, as is any number whose predecessor is
	 * even. A negative number is odd if its negation is odd.
	 * 
	 * @param val
	 * @return {@code true} if {@code val} is odd and {@code false} otherwise.
	 */
	public boolean isOdd(int val) {
    // Without recursion this could be: return val % 2 == 1;
		boolean odd = false;
		if(val < 0){
			val = val*-1;
		}
		if(val > 0){
			if(isEven(val-1) == true){
			odd = true;
			}
		}
		return odd;
	}

	/**
	 * Returns the sum of all values between 1 and n.
	 * 
	 * @param n
	 * @return the sum of all values between 0 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int sumN(int n) {
		int output;
    	if(n < 0){
    		throw new IllegalArgumentException("Cannot enter a number less than 0");
    	}
    	else if (n == 0){
    		output = 0;
    	}
    	else{
    		output = n + sumN(n-1);
    	}
    	return output;
	}

	/**
	 * Returns the multiplication of all values between 1 and n.
	 * 
	 * @param n
	 * @return the multiplication of all values between 1 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int factorial(int n) {
		int output;
		if(n < 0){
			throw new IllegalArgumentException("Factorial does not compute values less than 0");
		}
		else if(n == 0){
			output = 1;
		}
		else{
			output = n * factorial(n-1);
		}
		return output;
	}

	/**
	 * Returns 2 to the nth power. (2^n)
	 * 
	 * @param n
	 * @return 2 to the nth power.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int biPower(int n) {
		int output;
		if(n < 0){
			throw new IllegalArgumentException("n cannot be less than 0");
		}
		else if(n ==0){
			output = 1;
		}	
		else{
			output = 2 * biPower(n-1);
		}
        return output;
	}
}
