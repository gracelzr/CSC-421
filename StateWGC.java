/* 
    Assignment 1 Question 2
    Wolf Goat and Cabbage cross a river
    This file contains the state of the problem
*/

public class StateWGC 
{    
	int wgcArray[];
    
    public StateWGC(int wgcArray[]) { 
    	this.wgcArray = wgcArray; 
    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateWGC(StateWGC state) {
    	wgcArray = new int[8];  	
        for(int i=0; i<wgcArray.length; i++)
        	wgcArray[i] = state.wgcArray[i];
    }
    
    public boolean equals(Object o) {
        for(int i=0; i<8; i++){
            if(this.wgcArray[i] != ((StateWGC) o).wgcArray[i])
                return false;
        }
        return true;
    }
    
    public int hashCode() {
        return wgcArray[0]*10000000 + wgcArray[1]*1000000 + wgcArray[2]*100000 + wgcArray[3]*10000 + wgcArray[4]*1000
        + wgcArray[5]*100 + wgcArray[6]*10 + wgcArray[7];
    }    
    
    public String toString() {
        String s="";
        for(int i=0; i<8; i++){
            s += " " + this.wgcArray[i]; 
        }
        return s;
    }
}