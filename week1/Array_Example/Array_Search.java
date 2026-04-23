package week1.Array_Example;


/**
 * Write a description of class Array_Search here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Array_Search
{
    public static void main (String [] args){
        int a[] = {1,3,4,5,3,32};
        for(int i:a){
            if(i==932){
              System.out.println("Element found");   
            }
            // if first condition is false than the else condition executed 6 times because the element of array is 6
            // print " not found" 6 times, loop excuted six times;
            else{
                System.out.println("NOt found");
            }
            
           
        }
    }
}