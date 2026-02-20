package week1.week16;


/**
 * Write a description of class StringConvert here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringConvert
{
    public static void main( String [] args){
        String textGiven="I Am lalit";
        System.out.println("Your String Is:"+textGiven);
        
        System.out.println("Your Full String in Uppercase:"+ textGiven.toUpperCase());
        System.out.println("Your Full String In LowerCase:"+ textGiven.toLowerCase());
        
        String tremName = textGiven.trim();
        String[] words= tremName.split("\\s+");
        
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<words.length; i++)
        {
            String frist= words[i].substring(0,1).toUpperCase();
            String rest= words[i].substring(1).toLowerCase();
            sb.append(frist).append(rest).append(" ");
            
            
            
        }
        System.out.println(sb.toString().trim());
        
        
        
        
        
    }

}