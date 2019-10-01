import java.util.HashSet;
import java.util.Set;

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


        // Pour 12 to 8


        // Pour 8 to 12



        // Pour 12 to 3


        // Pour 3 to 12



        // Pour 8 to 3


        // Pour 3 to 8



        // Empty 12



        // Empty 8


        // Empty 3


        // ss = new StateJugs(s);
        // if(j0>0) {
        // 	ss.puzzleArray[i0][j0-1] = 0;
        // 	ss.puzzleArray[i0][j0]   = s.puzzleArray[i0][j0-1];
        // 	ss.j0--;
        // 	//System.out.println(ss);
        // 	set.add(ss);
        // }

        
        return set;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		ProblemJugs problem = new ProblemJugs();
		int[] jugsArray = {0,0,0,0};
		problem.initialState = new StateJugs(jugsArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		//System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		//System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		//System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		//System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		//System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		//System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		//System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
