

public class Maze {
//	new ImagePattern(start)
//	 Image start = new Image("pic/start.png");
	private int dimX, dimY; // Dimension Of maze
	private int[][] mazeArray; // Array to output (array w/ 0's and 1's)
	private boolean[][] visited; // Array to mark as visited
	private static char[][] outPut; // array to display maze appearance
	private boolean isWall = false;
	private static int[] mousePosition = new int[2];
	@SuppressWarnings("unused")
	private boolean endGame = false;
	String stringMaze = "";
	private int[] exitPoint= new int[2];
	int state=1;//hands at right wall
	
	// constructor takes in an array
	public Maze (int[][] array)
	{
	    	mazeArray = array; // that array reps the maze in 0's and 1's
	    	dimX = array.length; // store its row size (x)
	    	dimY = array[0].length; // store its column size (y)
			visited=new boolean[dimX][dimY];
			 setExitPoint();
	    	
	    	// set up mouses  initial position in maze
	    	getMousePosition()[0] = 0; // mouses x position
	    	getMousePosition()[1] = 2; // mouses y position
	    	
	    	// this array displays the maze
	    	outPut = new char[dimX][dimY]; 
	    	
	    	// create array that displays the "walls" (#) and "empty spaces" ( )
	    	for (int r = 0; r < dimX; r++) 
	    	{
	    		for(int c =0; c < dimY; c++) 
	    		{
	    			// replace 1 of array with space for path
					if (mazeArray[r][c] == 1)
	    				outPut[r][c] = ' ';
	    		
	    			// turning 0 to '#' because it reps a wall in maze
	    			else
	    				outPut[r][c] = '#';
	    		}	
	    	}
	    	
	    	placeMouse();
	    	stringMaze = getStringMaze();
	}
	   
	    
/*should print the maze (bird-view) that shows the walls and 
 * the available paths, and the path taken so far (if any)*/
	public void displayMaze()
	{
		for (int r = 0; r < dimX; r ++)
		{
			for (int c = 0; c < dimY; c++)
				System.out.print(outPut[r][c]);
			
			System.out.println();
		}
		System.out.println();
	}
		
	
/*	takes one step each time when it is called and displays 
 * the maze again. Use the algorithm that checks right turn first. 
 * And keeps checking counter-clockwise until finds a path.
*/		
	public void takeStep()
	{		
		// represents:
		//// mazeArray[row][col] = mazeArray[startX][startY]
		
		// initial position of mouse
		int x = getMousePosition()[0];
		int y = getMousePosition()[1];

		if (x == exitPoint[0] && y == exitPoint[1]) {
			System.out.println("NO MORE STEPS");
			stopSteps();
		}

		else {
			
			if(!lookLeft(x,y)){//
				System.out.println("Under Look Left, Current state: "+state);
				state=(state+3)%4;//four states only
				visited[x][y]=true;
				if(state==0){
					setMouse(x, y-1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y-1;
					
				}else if(state==1){
					setMouse(x+1, y);
					getMousePosition()[0] = x+1;
					getMousePosition()[1] = y;
					
				}else if(state==2){
					setMouse(x, y+1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y+1;
					
					
				}else{
					setMouse(x-1, y);
					getMousePosition()[0] = x-1;
					getMousePosition()[1] = y;
					
					
				}
				// moves to position where there is not a wall
				
			}else if(!lookForward(x,y)){
				System.out.println("Under Look Forward, Current state: "+state);
				visited[x][y]=true;
				if(state==0){
					setMouse(x, y-1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y-1;
					
				}else if(state==1){
					setMouse(x+1, y);
					getMousePosition()[0] = x+1;
					getMousePosition()[1] = y;
					
				}else if(state==2){
					setMouse(x, y+1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y+1;
					
					
				}else{
					setMouse(x-1, y);
					getMousePosition()[0] = x-1;
					getMousePosition()[1] = y;
					
					
				}
			}else if(!lookRight(x,y)){
				System.out.println("Under Look Right, Current state: "+state);
				state=(state+1)%4;
				visited[x][y]=true;
				if(state==0){
					setMouse(x, y-1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y-1;
					
				}else if(state==1){
					setMouse(x+1, y);
					getMousePosition()[0] = x+1;
					getMousePosition()[1] = y;
					
				}else if(state==2){
					setMouse(x, y+1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y+1;
					
					
				}else{
					setMouse(x-1, y);
					getMousePosition()[0] = x-1;
					getMousePosition()[1] = y;
					
					
				}
			}else{
				System.out.println("BackTracking, , Current state: "+state);
				state=(state+2)%4;//move opposite direction
				if(state==0){
					setMouse(x, y-1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y-1;
					
				}else if(state==1){
					setMouse(x+1, y);
					getMousePosition()[0] = x+1;
					getMousePosition()[1] = y;
					
				}else if(state==2){
					setMouse(x, y+1);
					getMousePosition()[0] = x;
					getMousePosition()[1] = y+1;
					
					
				}else{
					setMouse(x-1, y);
					getMousePosition()[0] = x-1;
					getMousePosition()[1] = y;
					
					
				}
				
				
				
			}
		}
				
	}
		
	/* runs and finds the solution all the way to the exit and 
	 * displays the maze showing the suggested path */
	public String findExit()
	{
		String s = "";
		while (getMousePosition() != exitPoint)
		{
			takeStep();
			s += getStringMaze();
			displayMaze();

			//this is where the check should happen:::change endgame to true;
		}

		System.out.println("\nThe mouse found the exit");
		return s;
	}
	       
	public int[] getMouse() {
	    	return getMousePosition();
	}
	    
    public void setMouse(int x, int y) {
    		outPut[getMousePosition()[0]][getMousePosition()[1]] = '*';
    		//mazeArray[mousePosition[0]][mousePosition[1]] = 0;
	    	getMousePosition()[0] = x;
	    	getMousePosition()[1] = y;
	    	placeMouse();
	}
	    
	    
	// method tests if a wall is present or not
	private boolean isWallThere(int x, int y) 
	{	
	
	if (x < mazeArray[0].length || y < mazeArray.length)
		{
		// testing if there is a wall or not
			if (mazeArray[x][y] == 1)
				isWall = false;
			else
				isWall = true;
		}
		else 
			isWall = true;
		
		return isWall;
		
	}
	    
	// places the mouse in the maze at mouse position point
	public void placeMouse() 
	{
	    	int x = getMousePosition()[0];
	    	int y = getMousePosition()[1];
	    	outPut[x][y] = '$';
	}


	public String getStringMaze() {
		String s = "";
		for (int i = 0; i < outPut.length; i++) {
			for (int j = 0; j < outPut[0].length; j++)
				s += outPut[i][j];
			s += "\n";
		}
		return s;
	}

	public void setExitPoint() {
		exitPoint[0] = mazeArray.length - 1;
		for (int i = 0; i < mazeArray[mazeArray.length - 1].length; i++) {
			if (mazeArray[mazeArray.length - 1][i] == 1)
				exitPoint[1] = i;
		}
	}

	public void stopSteps() {
		System.exit(0);
	}
	public int getMazeWidth()
	{
		return mazeArray.length;
	}
	public int getMazeHeight()
	{
		return mazeArray[0].length;
	}
	public static char[][] getOutPut()
	{
		char a[][] = outPut;
		return a;
	}


	public int[] getMousePosition() {
		return mousePosition;
	}


	public static void setMousePosition(int[] mousePosition) {
		Maze.mousePosition = mousePosition;
	}
	
	private boolean lookRight(int x,int y){
		if(state==0){//north
			return isWallThere(x+1,y);
			
		}else if(state==1){//east
			
			return isWallThere(x,y+1);
			
		}else if(state==2){//south
			return isWallThere(x-1,y);
			
		}else{//west
			return isWallThere(x,y-1);
			
		}
		
	}
	private boolean lookForward(int x,int y){
		if(state==0){//north
			return isWallThere(x,y-1);
			
		}else if(state==1){//east
			return isWallThere(x+1,y);
			
		}else if(state==2){//south
			return isWallThere(x,y+1);
			
		}else{//west
			return isWallThere(x-1,y);
			
		}
		
	}
	private boolean lookLeft(int x,int y){
		if(state==0){//north
			return isWallThere(x-1,y);
			
		}else if(state==1){//east
			return isWallThere(x,y-1);
			
		}else if(state==2){//south
			return isWallThere(x+1,y);
			
		}else{//west
			return isWallThere(x,y+1);
			
		}
		
	}
	public void setMazeArrayValue(int x,int y, int value){
	
		this.mazeArray[x][y]=value;
	}

}
