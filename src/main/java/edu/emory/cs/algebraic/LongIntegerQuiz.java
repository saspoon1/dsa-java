package edu.emory.cs.algebraic;
import java.util.*;
public class LongIntegerQuiz extends LongInteger{
    @Override
    protected void addDifferentSign(LongInteger n){
        int m = Math.max(digits.length, n.digits.length);
        byte[] result = new byte[m];
        // identify which LongInteger is bigger
        int findLarger = compareAbs(n);
        if (findLarger < 0 ) {
            //n is larger, so n is copied into result.
            sign = n.sign;
            System.arraycopy(n.digits, 0, result, 0, n.digits.length);
            //subtract this from result
            for (int i = 1; i <= digits.length; i++){
                result[i-1] = (byte) (result[i-1] - digits[i-1]);
                if (result[i-1] < 0){
                    result[i-1] += 10;
                    result[i] -= 1;
                }
            }
        }
        else if (findLarger > 0){
            //n is smaller, so this is copied into result.
            System.arraycopy(digits, 0, result, 0, digits.length);
            // subtract n from result
            for (int i = 1; i <= n.digits.length; i++){
                result[i-1] -= n.digits[i-1];
                if (result[i-1] < 0){
                    result[i-1] += 10;
                    result[i] -= 1;
                }
            }
        }
        else{
            result = new byte[1];
            result[0] = 0;
        }
        boolean needToRemoveZeros = false;
        if (result[m-1] == 0){
            needToRemoveZeros = true; //designate to remove
        }
        //find out how many to remove
        while (result[m-1] == 0){
            m = m-1;
        }
        digits = needToRemoveZeros ? Arrays.copyOf(result, m) : result;
    }


    static public void main(String[] args) {
        LongInteger x = new LongInteger("999");
        System.out.println(x);
        LongInteger y = new LongInteger("-1000");
        System.out.println(y);

        x.addDifferentSign(y);
        System.out.println(x);



    }

}
