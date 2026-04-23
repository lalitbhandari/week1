package week1.String_Class;


/**
 * Write a description of class StringCheck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringCheck
{
    public static void main(String [] args)
    {
        String a =new String("lalit");
        String b= new String("lalit");
        String c="lalit";
        String d="lalit";
        String e="Lalit";
        System.out.println(a.equals(b)); // true
        System.out.println(a.equals(c)); // true
        System.out.println(c.equals(d)); // true
        System.out.println(d.equals(e)); // false
        
        
        
        
    }
}