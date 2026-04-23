package week1.Array_Example;


/**
 * Write a description of class Arr_Sum here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arr_Sum
{
    public static void main(String[] args){
        int [] arr={1,2,3,2,1,1,3,23,90};
        int sum=0;
        for(int i=0; i<arr.length; i++){
           sum+=arr[i]; 
        }
        System.out.println(sum);
    }
    
}