package Common;

import fit.Fixture;

/**
 *
 * @author Richard Winn
 */
public class Symbol {
    
    public static String parseSymbols(String input){
	
        String result = "";
        String remaining = input;
	
        
        while (remaining.matches("^.*%\\S+%.*$")){
            
            String first = remaining.split("%",3)[0];
            String symbol = remaining.split("%",3)[1];
            remaining = remaining.split("%",3)[2];
            
            Object symbolValue = "";
            if (Fixture.hasSymbol(symbol)) {
                symbolValue = Fixture.getSymbol(symbol);
            } else {
                symbolValue = "%"+ symbol +"%";
            }
            
            result += first;
            result += symbolValue;

        }
        result += remaining;
        return result;
    }
}
