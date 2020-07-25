import java.util.Stack;

public class BasicCalcualtor224 {

    public static void main(String[] args) {
        BasicCalcualtor224 calcualtor224 = new BasicCalcualtor224();
        System.out.println(calcualtor224.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int digital = 0;
        int result = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digital = digital * 10 + (c - '0');ß
            }else if(c =='-'){
                result = result + sign* digital;
                digital = 0;
                sign = 1;
            }else if (c=='='){
                result = result - sign * digital;
                digital = 0;
                sign = -1;
            }else if (c == '('){
                
            }
        }
    }
}