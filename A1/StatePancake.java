/* 
    Assignment 1 Question 5
    Flip pancake
    This file contains the state of the problem
*/

public class StatePancake 
{    
	int pancakeArray[];
    
    public StatePancake(int pancakeArray[]) { 
    	this.pancakeArray = pancakeArray; 
    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StatePancake(StatePancake state) {
    	pancakeArray = new int[6];  	
        for(int i=0; i<pancakeArray.length; i++)
        	pancakeArray[i] = state.pancakeArray[i];
    }
    
    public boolean equals(Object o) {
        for(int i=0; i<6; i++){
            if(this.pancakeArray[i] != ((StatePancake) o).pancakeArray[i])
                return false;
        }
        return true;
    }
    
    public int hashCode() {
        return pancakeArray[0]*100000 + pancakeArray[1]*10000 + pancakeArray[2]*1000 +
               pancakeArray[3]*100 + pancakeArray[4]*10 + pancakeArray[5];
    }    
    
    public String toString() {
        String s="";
        for(int i=0; i<6; i++){
            s += " " + this.pancakeArray[i]; 
        }
        return s;
    }
}