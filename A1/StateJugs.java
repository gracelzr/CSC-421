public class StateJugs
{    
    int[] jugsArray;
    
    public StateJugs(int[] jugsArray) { 
    	this.jugsArray = jugsArray; 
    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateJugs(StateJugs state) {
        jugsArray = new int[4];
        for(int i=0; i<4; i++)
        	jugsArray[i] = state.jugsArray[i];
    }
    
    public boolean equals(Object o) {
        StateJugs state = (StateJugs) o;
        
        for (int i=0; i<this.jugsArray.length; i++) {
            if (this.jugsArray[i] != state.jugsArray[i])
                return false;
        }
        return true;
    }
    
    public int hashCode() {
        return jugsArray[0]*1000 + jugsArray[1]*100 + jugsArray[2]*10 + jugsArray[3];
    }

    public String toString() {
    	String ret = "";
        for (int i=0; i<jugsArray.length; i++)
            ret += " " + this.jugsArray[i];
        return ret;
    }
}