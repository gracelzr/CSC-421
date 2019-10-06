/* 
    Assignment 1 Question 5
    Flip pancake to get the smallest on top and the largest on bottem
*/


import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ProblemPancake extends Problem {
    // rank from top to bottom. 0 -- top, 5 -- bottom
    static final int rank0 = 0;
    static final int rank1 = 1;
    static final int rank2 = 2;
    static final int rank3 = 3;
    static final int rank4 = 4;
    static final int rank5 = 5;


	boolean goal_test(Object state) {
        StatePancake pan_state = (StatePancake) state;
        
		if(pan_state.pancakeArray[rank0] != 0 || pan_state.pancakeArray[rank1] != 1 || pan_state.pancakeArray[rank2] != 2
            || pan_state.pancakeArray[rank3] != 3 || pan_state.pancakeArray[rank4] != 4 || pan_state.pancakeArray[rank5] != 5)
			return false;
        
        return true;
	}

  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StatePancake s = (StatePancake) state;
        //int i0 = s.i0, j0 = s.j0;
        int val0,val1,val2,val3,val4,val5, val6 = 0;
        StatePancake ss; //successor state
        
        // Flip 1 pancake, flip top one, nothing change
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        ss.pancakeArray[rank0] = val0;
        if(isVaild(ss))
            set.add(ss);

        // Flip 2 pancakes
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        val1 = ss.pancakeArray[rank1];       
        ss.pancakeArray[rank0] = val1;
        ss.pancakeArray[rank1] = val0;
        if(isVaild(ss))
            set.add(ss);

        // Flip 3 pancakes
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        val1 = ss.pancakeArray[rank1];
        val2 = ss.pancakeArray[rank2];
        ss.pancakeArray[rank0] = val2;
        ss.pancakeArray[rank1] = val1;
        ss.pancakeArray[rank2] = val0;
        if(isVaild(ss))
            set.add(ss);

        // Flip 4 pancakes
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        val1 = ss.pancakeArray[rank1];
        val2 = ss.pancakeArray[rank2];
        val3 = ss.pancakeArray[rank3];
        ss.pancakeArray[rank0] = val3;
        ss.pancakeArray[rank1] = val2;
        ss.pancakeArray[rank2] = val1;
        ss.pancakeArray[rank3] = val0;
        if(isVaild(ss))
            set.add(ss);

        // Flip 5 pancakes
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        val1 = ss.pancakeArray[rank1];
        val2 = ss.pancakeArray[rank2];
        val3 = ss.pancakeArray[rank3];
        val4 = ss.pancakeArray[rank4];
        ss.pancakeArray[rank0] = val4;
        ss.pancakeArray[rank1] = val3;
        ss.pancakeArray[rank2] = val2;
        ss.pancakeArray[rank3] = val1;
        ss.pancakeArray[rank4] = val0;
        if(isVaild(ss))
            set.add(ss);

        // Flip 6 pancakes
        ss = new StatePancake(s);
        val0 = ss.pancakeArray[rank0];
        val1 = ss.pancakeArray[rank1];
        val2 = ss.pancakeArray[rank2];
        val3 = ss.pancakeArray[rank3];
        val4 = ss.pancakeArray[rank4];
        val5 = ss.pancakeArray[rank5];
        ss.pancakeArray[rank0] = val5;
        ss.pancakeArray[rank1] = val4;
        ss.pancakeArray[rank2] = val3;
        ss.pancakeArray[rank3] = val2;
        ss.pancakeArray[rank4] = val1;
        ss.pancakeArray[rank5] = val0;
        if(isVaild(ss))
            set.add(ss);

        return set;
    }
	

    private boolean isVaild(StatePancake state){
        // check range
        for(int i=0; i<6; i++){
            if(state.pancakeArray[i] < 0 || state.pancakeArray[i] > 5)
                return false;
        }

        return true;

    }

	double step_cost(Object fromState, Object toState) { return 1; }

    /* Heuristic Function */
	public double h(Object state) {
        StatePancake st = (StatePancake) state;
        int heuristic = 0;
        for (int i = 1; i < st.pancakeArray.length; i++){
            if(st.pancakeArray[i] > st.pancakeArray[i-1])
                heuristic--;      
        }
        return heuristic;
    }


	public static void main(String[] args) throws Exception {
		ProblemPancake problem = new ProblemPancake();
		int [] pancakeArray = {1,0,3,5,2,4};
		problem.initialState = new StatePancake(pancakeArray); 
		
		Search search  = new Search(problem);
        BufferedWriter writer = new BufferedWriter(new FileWriter("Q5_Output_Heuristic.txt"));
        writer.write("GreedyBestFirst------------------------");
        //writer.write("\nGreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
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
