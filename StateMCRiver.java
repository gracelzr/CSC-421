/* 
    Assignment 1 Question3
    Three missionaries and three cannibals cross a river
    This file contains the state of the problem
*/

public class StateMCRiver 
{    
	int totalPeopleArray[];
    
    public StateMCRiver(int totalPeopleArray[]) { 
    	this.totalPeopleArray = totalPeopleArray; 
    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateMCRiver(StateMCRiver state) {
    	// totalPeopleArray length is 6
    	totalPeopleArray = new int[6];  	
        for(int i=0; i<6; i++)
        	totalPeopleArray[i] = state.totalPeopleArray[i];
    }
    
    public boolean equals(Object o) {
        for(int i=0; i<6; i++){
            if(this.totalPeopleArray[i] != ((StateMCRiver) o).totalPeopleArray[i])
                return false;
        }
        return true;
    }
    
    public int hashCode() {
        return totalPeopleArray[0]*100000 + totalPeopleArray[1]*10000 + totalPeopleArray[2]*1000 +
               totalPeopleArray[3]*100 + totalPeopleArray[4]*10 + totalPeopleArray[5];
    }    
    
    public String toString() {
        String s="";
        for(int i=0; i<6; i++){
            s += " " + this.totalPeopleArray[i]; 
        }
        return s;
    }
}