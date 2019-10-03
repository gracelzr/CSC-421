import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ProblemJugs extends Problem {
    
    static final int jug12 = 0;
    static final int jug8 = 1;
    static final int jug3 = 2;
    static final int ground = 3;

    private boolean isValid(StateJugs state) {
        int[] jugs = state.jugsArray;

        if(jugs[0] > 12 || jugs[0]<0) return false;
        if(jugs[1] > 8 || jugs[1]<0) return false;
        if(jugs[2] > 3 || jugs[2]<0) return false;

        return true;
    }


	boolean goal_test(Object state) {
        StateJugs jugs_state = (StateJugs) state;
        
        for(int i=0; i<jugs_state.jugsArray.length; i++)
        	if(jugs_state.jugsArray[i] == 1)
        		return true;
        
        return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateJugs s = (StateJugs) state;      
        StateJugs ss; //successor state
        int waterIn12, waterIn8, waterIn3, roomIn12, roomIn8, roomIn3 = 0;

        // Fill 12
        ss = new StateJugs(s);
        if(ss.jugsArray[jug12] < 12) {
            ss.jugsArray[jug12] += 12 - ss.jugsArray[jug12];
            if(isValid(ss))
                set.add(ss);
        }


        // Fill 8
        ss = new StateJugs(s);
        if(ss.jugsArray[jug8] < 8) {
            ss.jugsArray[jug8] += 8 - ss.jugsArray[jug8];
            if(isValid(ss))
                set.add(ss);
        }

        // Fill 3
        ss = new StateJugs(s);
        if(ss.jugsArray[jug3] < 3) {
            ss.jugsArray[jug3] += 3 - ss.jugsArray[jug3];
            if(isValid(ss))
                set.add(ss);
        }

        // Pour 12 to 8
        ss = new StateJugs(s);
        waterIn12 = ss.jugsArray[jug12];
        roomIn8 = 8 - ss.jugsArray[jug8];
        // if Jug 12 has water and Jug 8 has room, then pour
        if(waterIn12 > 0 && roomIn8 > 0){
            if(waterIn12 > roomIn8){
                ss.jugsArray[jug12] -= roomIn8;
                ss.jugsArray[jug8] += roomIn8;
            }else{
                ss.jugsArray[jug12] -= waterIn12;
                ss.jugsArray[jug8] += waterIn12;
            }
            if(isValid(ss))
                set.add(ss);
        }

        // Pour 8 to 12
        ss = new StateJugs(s);
        waterIn8 = ss.jugsArray[jug8];
        roomIn12 = 12 - ss.jugsArray[jug12];
        if(waterIn8 > 0 && roomIn12 > 0){
            if(waterIn8 > roomIn12){
                ss.jugsArray[jug8] -= roomIn12;
                ss.jugsArray[jug12] += roomIn12;
            }else{
                ss.jugsArray[jug8] -= waterIn8;
                ss.jugsArray[jug12] += waterIn8;
            }
            if(isValid(ss))
                set.add(ss);
        }

        // Pour 12 to 3
        ss = new StateJugs(s);
        waterIn12 = ss.jugsArray[jug12];
        roomIn3 = 3 - ss.jugsArray[jug3];
        // if Jug 12 has water and Jug 3 has room, then pour
        if(waterIn12 > 0 && roomIn3 > 0){
            if(waterIn12 > roomIn3){
                ss.jugsArray[jug12] -= roomIn3;
                ss.jugsArray[jug3] += roomIn3;
            }else{
                ss.jugsArray[jug12] -= waterIn12;
                ss.jugsArray[jug3] += waterIn12;
            }
            if(isValid(ss))
                set.add(ss);
        }

        // Pour 3 to 12
        ss = new StateJugs(s);
        waterIn3 = ss.jugsArray[jug3];
        roomIn12 = 12 - ss.jugsArray[jug12];
        if(waterIn3 > 0 && roomIn12 > 0){
            if(waterIn3 > roomIn12){
                ss.jugsArray[jug3] -= roomIn12;
                ss.jugsArray[jug12] += roomIn12;
            }else{
                ss.jugsArray[jug3] -= waterIn3;
                ss.jugsArray[jug12] += waterIn3;
            }
            if(isValid(ss))
                set.add(ss);
        }


        // Pour 8 to 3
        ss = new StateJugs(s);
        waterIn8 = ss.jugsArray[jug8];
        roomIn3 = 3 - ss.jugsArray[jug3];
        if(waterIn8 > 0 && roomIn3 > 0){
            if(waterIn8 > roomIn3){
                ss.jugsArray[jug8] -= roomIn3;
                ss.jugsArray[jug3] += roomIn3;
            }else{
                ss.jugsArray[jug8] -= waterIn8;
                ss.jugsArray[jug3] += waterIn8;
            }
            if(isValid(ss))
                set.add(ss);
        }

        // Pour 3 to 8
        ss = new StateJugs(s);
        waterIn3 = ss.jugsArray[jug3];
        roomIn8 = 8 - ss.jugsArray[jug8];
        if(waterIn3 > 0 && roomIn8 > 0){
            if(waterIn3 > roomIn8){
                ss.jugsArray[jug3] -= roomIn8;
                ss.jugsArray[jug8] += roomIn8;
            }else{
                ss.jugsArray[jug3] -= waterIn3;
                ss.jugsArray[jug8] += waterIn3;
            }
            if(isValid(ss))
                set.add(ss);
        }


        // Empty 12
        ss = new StateJugs(s);
        if(ss.jugsArray[jug12] > 0) {
            ss.jugsArray[ground] += ss.jugsArray[jug12];
            ss.jugsArray[jug12] = 0;
            if(isValid(ss))
                set.add(ss);
        }

        // Empty 8
        ss = new StateJugs(s);
        if(ss.jugsArray[jug8] > 0) {
            ss.jugsArray[ground] += ss.jugsArray[jug8];
            ss.jugsArray[jug8] = 0;
            if(isValid(ss))
                set.add(ss);
        }

        // Empty 3
        ss = new StateJugs(s);
        if(ss.jugsArray[jug3] > 0) {
            ss.jugsArray[ground] += ss.jugsArray[jug3];
            ss.jugsArray[jug3] = 0;
            if(isValid(ss))
                set.add(ss);
        }
        
        return set;
    }
	
	double step_cost(Object fromState, Object toState) {
        int costs=0;
        StateJugs prev = (StateJugs) fromState;
        StateJugs next = (StateJugs) toState;

        for(int i=0; i< prev.jugsArray.length; i++){
            if(next.jugsArray[i] - prev.jugsArray[i] >0)
                costs+=(next.jugsArray[i] - prev.jugsArray[i]);
        }

        return costs;
    }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		ProblemJugs problem = new ProblemJugs();
		int[] jugsArray = {0,0,0,0};
		problem.initialState = new StateJugs(jugsArray); 
		
		Search search  = new Search(problem);
		
        BufferedWriter writer = new BufferedWriter(new FileWriter("Q4_Output.txt"));

		writer.write("TreeSearch------------------------");
		writer.write("\nBreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		writer.write("\nUniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		writer.write("\nDepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		writer.write("\nGreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		writer.write("\nAstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		writer.write("\n\nGraphSearch----------------------");
		writer.write("\nBreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		writer.write("\nUniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		writer.write("\nDepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		writer.write("\nGreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		writer.write("\nAstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		writer.write("\n\nIterativeDeepening----------------------");
		writer.write("\nIterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		writer.write("\nIterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
         
        writer.close();
	}
	
}
