/* 
    Assignment 1 Question 3
    Three missionaries and three cannibals cross a river
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

public class ProblemMCRiver extends Problem {
    
    static final int cannL = 0;
    static final int missL = 1;
    static final int boatL = 2;
    static final int cannR = 3;
    static final int missR = 4;
    static final int boatR = 5;

    // From left of the river to right of the river
    // Right : 3 cannibals, 3 missionaries, 1 boat
	boolean goal_test(Object state) {
        StateMCRiver mcr_state = (StateMCRiver) state;
        
		if(mcr_state.totalPeopleArray[cannR] != 3 || mcr_state.totalPeopleArray[missR] != 3 || mcr_state.totalPeopleArray[boatR] != 1)
			return false;
        
        return true;
	}

  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateMCRiver s = (StateMCRiver) state;
        //int i0 = s.i0, j0 = s.j0;
        
        StateMCRiver ss; //successor state
        
        // 1 cannibal from left to right
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannL] -= 1;
        ss.totalPeopleArray[cannR] += 1;
        ss.totalPeopleArray[boatL] -= 1;
        ss.totalPeopleArray[boatR] += 1;
        if(isVaild(ss))
            set.add(ss);

        // 1 cannibal from right to left
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannR] -= 1;
        ss.totalPeopleArray[cannL] += 1;
        ss.totalPeopleArray[boatR] -= 1;
        ss.totalPeopleArray[boatL] += 1;
        if(isVaild(ss))
            set.add(ss);

        // 2 cannibal from left to right
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannL] -= 2;
        ss.totalPeopleArray[cannR] += 2;
        ss.totalPeopleArray[boatL] -= 1;
        ss.totalPeopleArray[boatR] += 1;
        if(isVaild(ss))
            set.add(ss);


        // 2 cannibal from right to left
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannR] -= 2;
        ss.totalPeopleArray[cannL] += 2;
        ss.totalPeopleArray[boatR] -= 1;
        ss.totalPeopleArray[boatL] += 1;
        if(isVaild(ss))
            set.add(ss);



        // 1 missionary from left to right
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[missL] -= 1;
        ss.totalPeopleArray[missR] += 1;
        ss.totalPeopleArray[boatL] -= 1;
        ss.totalPeopleArray[boatR] += 1;
        if(isVaild(ss))
            set.add(ss);


        // 1 missionary from right to left
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[missR] -= 1;
        ss.totalPeopleArray[missL] += 1;
        ss.totalPeopleArray[boatR] -= 1;
        ss.totalPeopleArray[boatL] += 1;
        if(isVaild(ss))
            set.add(ss);


        // 2 missionary from left to right
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[missL] -= 2;
        ss.totalPeopleArray[missR] += 2;
        ss.totalPeopleArray[boatL] -= 1;
        ss.totalPeopleArray[boatR] += 1;
        if(isVaild(ss))
            set.add(ss);


        // 2 missionary from right to left
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[missR] -= 2;
        ss.totalPeopleArray[missL] += 2;
        ss.totalPeopleArray[boatR] -= 1;
        ss.totalPeopleArray[boatL] += 1;
        if(isVaild(ss))
            set.add(ss);



        // 1 cannibal and 1 missionary from left to right
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannL] -= 1;
        ss.totalPeopleArray[cannR] += 1;
        ss.totalPeopleArray[missL] -= 1;
        ss.totalPeopleArray[missR] += 1;
        ss.totalPeopleArray[boatL] -= 1;
        ss.totalPeopleArray[boatR] += 1;
        if(isVaild(ss))
            set.add(ss);

        // 1 cannibal and 1 missionary from right to left
        ss = new StateMCRiver(s);
        ss.totalPeopleArray[cannR] -= 1;
        ss.totalPeopleArray[cannL] += 1;
        ss.totalPeopleArray[missR] -= 1;
        ss.totalPeopleArray[missL] += 1;
        ss.totalPeopleArray[boatR] -= 1;
        ss.totalPeopleArray[boatL] += 1;
        if(isVaild(ss))
            set.add(ss);
       
        return set;
    }
	

    private boolean isVaild(StateMCRiver state){
        // check negative
        for(int i=0; i<6; i++){
            if(state.totalPeopleArray[i] < 0)
                return false;
        }
        // check total cann# > 3, miss# > 3, boat# > 1
        if(state.totalPeopleArray[cannL] + state.totalPeopleArray[cannR] > 3 
            || state.totalPeopleArray[missL] + state.totalPeopleArray[missR] > 3
            || state.totalPeopleArray[boatL] + state.totalPeopleArray[boatR] > 1)
            return false;

        // check one side cann# > 3, miss# > 3, boat# > 1
        if(state.totalPeopleArray[cannL] > 3 || state.totalPeopleArray[missL] > 3 || state.totalPeopleArray[boatL] > 1
            || state.totalPeopleArray[cannR] > 3 || state.totalPeopleArray[missR] > 3 || state.totalPeopleArray[boatR] > 1)
            return false;

        // check cann# > miss#
        if(state.totalPeopleArray[cannL] > state.totalPeopleArray[missL] && state.totalPeopleArray[missL] != 0
            || state.totalPeopleArray[cannR] > state.totalPeopleArray[missR] && state.totalPeopleArray[missR] != 0)
            return false;

        return true;

    }

	double step_cost(Object fromState, Object toState) { return 1; }

    /* Heuristic Function 2(n-2)+1*/
	public double h(Object state) {
        StateMCRiver st = (StateMCRiver) state;
        return 2*(st.totalPeopleArray[cannL] + st.totalPeopleArray[missL]) - 3;
    }


	public static void main(String[] args) throws Exception {
		ProblemMCRiver problem = new ProblemMCRiver();
		int [] totalPeopleArray = {3,3,1,0,0,0};
		problem.initialState = new StateMCRiver(totalPeopleArray); 
		
		Search search  = new Search(problem);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("Q3_Output_Heuristic.txt"));
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
