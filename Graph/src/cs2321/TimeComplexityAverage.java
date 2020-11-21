package cs2321;

import java.lang.annotation.*;

/**
 * Claim a method's average time requirements.
 * 
 * Valid values are:
 *     O(1), O(n), O(n^2), O(n^3), O(lg n), O(n lg n), O(n^2 lg n), O(2^n), O(?)

 *     O(?) is used for methods whose complexity isn't relevant. 
 *          (Such as "main" methods used for testing purposes) 
 *          
 * Example syntax:
 *    @TimeComplexityAverage("O(n)")
 *    METHOD_DECLARATION
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeComplexityAverage {
    String value()  default "[unassigned]"; 
}
