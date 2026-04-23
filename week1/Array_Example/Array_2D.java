package week1.Array_Example;


/**
 * Write a description of class Array_2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Array_2D
{
    public static void main (String [] args){
        int [][] arr={{2,3,4},{3,4,2},{4,5,3}};
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j]+" ");
                
            }   
            System.out.println();
        }
        
    }
}