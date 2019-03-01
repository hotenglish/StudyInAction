package answer;
 public class trangle {
    
    public trangle() 
    {
        
    }    
    public static void main(String args[])
    {
        int rows,cols;
        for(rows=1;rows<=5;rows++)
        {
            for(int space=rows;space<=4;space++)
            System.out.print(" "); 
            for (cols = 1; cols <= 2 * rows - 1; cols++)
             {
                 System.out.print("*");
             }
            System.out.println();  
        }
    }
}