// The application reads domain names from standard input
// and returns the reverse domain names in sorted order

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseDomain implements Comparable<ReverseDomain>{
     
    private final String[] fields;
    private final String[] temp;
    private final int n;

    // Store fields in reverse order
    public ReverseDomain(String name){   

        fields = name.split("\\@");      // Split the string at "@" in order to seperate the username from the domain name
        String s = fields[0];
        
        for (int i = 1; i < fields.length; i++)
            s = fields[i];
            temp = s.split("\\.");       // Split the domain names strings at "."
        n = temp.length;
    }

    // Return string representation - fields, delimited by "."
    public String toString(){   
    
        if (n == 0) return "";
        String domainOnly = temp[0];
        
        for (int i = 1; i < temp.length; i++)
            domainOnly = temp[i] + "." + domainOnly;
        return domainOnly;
    }

    // Compare by reverse domain name
    public int compareTo(ReverseDomain that){  
        
        for (int i = 0; i < Math.min(this.n, that.n); i++){
            String s = this.temp[this.n - i - 1];
            String t = that.temp[that.n - i - 1];
            int c = s.compareTo(t);
            if (c < 0) return -1;
            else if (c > 0) return +1;
        }
        return this.n - that.n;
    }


    // Test client
    public static void main(String[] args){     
        // Read in the domain names from file 
        Scanner in = new Scanner(System.in);
        List<String> tokens = new ArrayList<String>();
        while (in.hasNext()) {
            tokens.add(in.nextLine());
        }
        String[] names = tokens.toArray(new String[0]);
         
        ReverseDomain[] domains = new ReverseDomain[names.length];
        
        for (int i = 0; i < domains.length; i++){
            domains[i] = new ReverseDomain(names[i]);
        }
        // Sort the domain names
        Arrays.sort(domains);        
        // Print results
        for (int i = 0; i < domains.length; i++){         
            System.out.println(domains[i]);
        }
    }
}