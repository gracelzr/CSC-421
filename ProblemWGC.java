/* 
    Assignment 1 Question 2
    Wolf Goat and Cabbage cross a river, assume from left to right
    This file contains the problem states and goals
*/


import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ProblemWGC extends Problem {
    
    static final int wolfL = 0;
    static final int goatL = 1;
    static final int cabbL = 2;
    static final int boatL = 3;
    static final int wolfR = 4;
    static final int goatR = 5;
    static final int cabbR = 6;
    static final int boatR = 7;

    // From left of the river to right of the river
    // Right : 1 wolf, 1 goat, 1 boat
	boolean goal_test(Object state) {
        StateWGC wgc_state = (StateWGC) state;
        
		if(wgc_state.wgcArray[wolfR] == 1 && wgc_state.wgcArray[goatR] == 1 && wgc_state.wgcArray[cabbR] == 1)
			return true;
        
        return false;
	}

  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateWGC s = (StateWGC) state;
        
        StateWGC ss; //successor state
        
        // 1 wolf from left to right
        ss = new StateWGC(s);
        ss.wgcArray[wolfL] = 0;
        ss.wgcArray[wolfR] = 1;
        ss.wgcArray[boatL] = 0;
        ss.wgcArray[boatR] = 1;
        if(isVaild(ss))
            set.add(ss);

        // 1 wolf from right to left
        ss = new StateWGC(s);
        ss.wgcArray[wolfR] = 0;
        ss.wgcArray[wolfL] = 1;
        ss.wgcArray[boatR] = 0;
        ss.wgcArray[boatL] = 1;
        if(isVaild(ss))
            set.add(ss);


        // 1 goat from left to right
        ss = new StateWGC(s);
        ss.wgcArray[goatL] = 0;
        ss.wgcArray[goatR] = 1;
        ss.wgcArray[boatL] = 0;
        ss.wgcArray[boatR] = 1;
        if(isVaild(ss))
            set.add(ss);


        // 1 goat from right to left
        ss = new StateWGC(s);
        ss.wgcArray[goatR] = 0;
        ss.wgcArray[goatL] = 1;
        ss.wgcArray[boatR] = 0;
        ss.wgcArray[boatL] = 1;
        if(isVaild(ss))
            set.add(ss);

        // 1 cabbage from left to right
        ss = new StateWGC(s);
        ss.wgcArray[cabbL] = 0;
        ss.wgcArray[cabbR] = 1;
        ss.wgcArray[boatL] = 0;
        ss.wgcArray[boatR] = 1;
        if(isVaild(ss))
            set.add(ss);


        // 1 cabbage from right to left
        ss = new StateWGC(s);
        ss.wgcArray[cabbR] = 0;
        ss.wgcArray[cabbL] = 1;
        ss.wgcArray[boatR] = 0;
        ss.wgcArray[boatL] = 1;
        if(isVaild(ss))
            set.add(ss);
       
       // boat from left to right, carry no item
        ss = new StateWGC(s);
        ss.wgcArray[boatL] = 0;
        ss.wgcArray[boatR] = 1;
        if(isVaild(ss))
            set.add(ss);


        // boat from right to left, carry no item
        ss = new StateWGC(s);
        ss.wgcArray[boatR] = 0;
        ss.wgcArray[boatL] = 1;
        if(isVaild(ss))
            set.add(ss);

        return set;
    }
	

    private boolean isVaild(StateWGC state){
        // check negative
        for(int i=0; i<8; i++){
            
            if(state.wgcArray[i] < 0)
                return false;
        }
        //System.out.print("[" + state.wgcArray[0] + state.wgcArray[1] + state.wgcArray[2] + state.wgcArray[3] + state.wgcArray[4]
        //    + state.wgcArray[5] + state.wgcArray[6] + state.wgcArray[7] + "]\n");
        if(state.wgcArray[wolfL] + state.wgcArray[wolfR] != 1 
            || state.wgcArray[goatL] + state.wgcArray[goatR] != 1
            || state.wgcArray[cabbL] + state.wgcArray[cabbR] != 1
            || state.wgcArray[boatL] + state.wgcArray[boatR] != 1)
            return false;

        
        if(state.wgcArray[wolfL] > 1 || state.wgcArray[goatL] > 1 || state.wgcArray[cabbL] > 1 || state.wgcArray[boatL] > 1 
            || state.wgcArray[wolfR] > 1 || state.wgcArray[goatR] > 1 || state.wgcArray[cabbR] > 1 || state.wgcArray[boatR] > 1 )
            return false;

        // check wolf and goat at same side, goat and cabbage at same side
        if(state.wgcArray[wolfL] == 1 && state.wgcArray[goatL] == 1 && state.wgcArray[boatL] != 1)
            return false;

        if(state.wgcArray[wolfR] == 1 &&  state.wgcArray[goatR] == 1 && state.wgcArray[boatR] != 1)
            return false;
        
        if(state.wgcArray[cabbL] == 1 && state.wgcArray[goatL] == 1 && state.wgcArray[boatL] != 1)
            return false;

        if(state.wgcArray[cabbR] == 1 && state.wgcArray[goatR] == 1 && state.wgcArray[boatR] != 1)
            return false;

        return true;

    }

	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) {
        StateWGC st = (StateWGC) state;
        return 2*(st.wgcArray[wolfL] + st.wgcArray[goatL] + st.wgcArray[cabbL]) - 5;
    }


	public static void main(String[] args) throws Exception {
		ProblemWGC problem = new ProblemWGC();
		int [] wgcArray = {1,1,1,1,0,0,0,0};
		problem.initialState = new StateWGC(wgcArray); 
		
		Search search  = new Search(problem);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("Q2_Output_Heuristic.txt"));
        writer.write("GreedyBestFirst------------------------");
        writer.write("\nGreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        writer.write("\nGreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());

        writer.write("\n\nAstar------------------------");
        writer.write("\nAstarTreeSearch:\t" + search.AstarTreeSearch());
        writer.write("\nAstarGraphSearch:\t" + search.AstarGraphSearch());

        writer.write("\n\nBreadthFirst------------------------");
        writer.write("\nBreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        writer.write("\nBreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        
        writer.write("\n\nDepthFirst------------------------");
        writer.write("\nDepthFirstTreeSearch:\t" + search.DepthFirstTreeSearch());
        writer.write("\nDepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
        
        writer.write("\n\nUniformCost------------------------");
        writer.write("\nUniformCostTreeSearch:\t" + search.UniformCostTreeSearch());
        writer.write("\nUniformCostGraphSearch:\t" + search.UniformCostGraphSearch());

        writer.write("\n\nIterativeDeepening----------------------");
        writer.write("\nIterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        writer.write("\nIterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
        writer.close();
	}
	
}
