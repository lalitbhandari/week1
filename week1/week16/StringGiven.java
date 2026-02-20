package week1.week16;


/**
 * Write a description of class StringGiven here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringGiven
{
    public static void main(String [] args){
        String givenText= "My Name is Lalit Bhandari";
        System.out.println("Char at givem index"+givenText.charAt(0));
        int position = givenText.indexOf('l');
        System.out.println(position);
        
           if(givenText.contains("Java")) {
          int index = givenText.indexOf("Java");
          
            System.out.println("Found at index: " + index);
           } else {
               
            System.out.println("Word not found");
   }
        
        
        
    }
}