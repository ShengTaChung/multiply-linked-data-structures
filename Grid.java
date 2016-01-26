
public class Grid {
	private GNode head;
	private int numRow, numCol;
	public Grid(){
		numRow = 10;
		numCol = 6;
		GNode[][] grid = new GNode[10][6];	
		for(int i = 0; i < 10; i++){					// Make empty grid with not linked nodes
			for(int j = 0; j < 6; j++){
				grid[i][j] = new GNode(new Value());
			}
		}
		head = grid[0][0];								// head node assigned
		for(int i = 0; i < 10; i++){					// link node right and down
			for(int j = 0; j < 6; j++){
				if(j < 5)
					grid[i][j].right = grid[i][j+1];
				else
					grid[i][j].right = grid[i][0];		// link last to first in row
				if(i < 9)
					grid[i][j].down = grid[i+1][j];
				else									// link last to first in column	//		  0	  1   2   3	
					grid[i][j].down = grid[0][j];										// 		-----------------
			}																			// row 0| 1 | 2 | 3 | 4 | 
		}																				// 		-----------------
	}																					// row 1| 5 | 6 | 7 | 8 |
	public void display(){																//		-----------------
		int count = 1;																	// row 2| 9 | 10| 11| 12|
		GNode current = head;															// 		-----------------
		String message = String.format("%10s","");					// y-axis print col#
		for(int i = 0; i < numCol; i++)
			message = message + String.format("%-10s","col " + i);
		while(count <= (numRow * numCol)){
			if( count % numCol == 1)								// x-axis print row#
				message = message + "\n" + String.format("%-10s","row " + (count % numCol + count / numCol - 1) ); 
			else if( numCol == 1)									// when there is only one col
				message = message + "\n" + String.format("%-10s", "row " + (count-1) ) ;
			if( current.data.isNull() )
				message = message + String.format("%10s", "");		//if sval is null, print ""
			else
				message = message + current.data.toString();
			count++;
			current = current.right;								// advance to right
			if( count % numCol == 1 || numCol == 1){				// advance to down, first node in next row is 1 number greater than last node in previous row
				current = current.down;								// and when number of col == 1
			}
		}
		System.out.println(message);
	}
	public void assignCell(int row, int col, String wValue){
		GNode current = moveFromHeadToPosition(row,col);
		if( Value.isString(wValue) )
			current.data.setSval(wValue.substring(1, wValue.length())); // store as string without quote mark
		else
			current.data.setDval(Double.parseDouble(wValue));			// convert to double and store
	}																							
	public int getNumRows(){ return(this.numRow); }												//		  0	  1   2   3	  4
	public int getNumCols(){ return(this.numCol); }												// 		---------------------	
	public void fill(int fRow, int fCol, int toRow, int toCol, String wValue){					// row 0|   |   |   |   |   |
		GNode current = moveFromHeadToPosition(fRow, fCol);	//all same as number method 		// 		---------------------
		int prevRow = fRow;																		// row 1|   | 1 | 2 |   |   |
		int prevCol = fCol;																		//		---------------------
		for(int i = fRow; i <= toRow; i++){														// row 2|   | 3 | 4 |   |   |
			for(int j = fCol; j <= toCol; j++){													// 		---------------------
				current = moveCurrentPositionTo(current, prevRow, prevCol, i, j );				// row 3|   |   |   |   |   |
				if( Value.isString(wValue) )													//		---------------------				
					current.data.setSval(wValue.substring(1, wValue.length())); // store as string without quote mark
				else
					current.data.setDval(Double.parseDouble(wValue));			// convert to double and store
				prevRow = i;
				prevCol = j;
			}
		}
	}
	public void number(int fRow, int fCol, int toRow, int toCol){
		GNode current = moveFromHeadToPosition(fRow, fCol);				// move to first position
		int prevRow = fRow;												// for now set previous row same as first Row position
		int prevCol = fCol;												// for now set previous col same as first col position
		int count = 0;														// value
		for(int i = fRow; i <= toRow; i++){
			for(int j = fCol; j <= toCol; j++){
				current = moveCurrentPositionTo(current, prevRow, prevCol, i, j );// move to next position, if first time in loop will be 0,0,0,0
				current.data.setDval(count);												//set value to cell
				count++;																	// value +1
				prevRow = i;																// save this i for next i
				prevCol = j;																// save this j for next j
			}
		}
	}	
	public void addCells(int fNRow, int fNCol, int sNRow, int sNCol, int dNRow, int dNCol){
		GNode nodeOne = moveCurrentPositionTo(head, 0, 0, fNRow, fNCol);
		GNode nodeTwo = moveCurrentPositionTo(nodeOne, fNRow, fNCol, sNRow, sNCol);
		Value newValue = nodeOne.getData().plus(nodeTwo.getData());
		if(!newValue.getTag().equals("INVALID")){										  // check both value are double
			moveCurrentPositionTo(nodeTwo, sNRow, sNCol, dNRow, dNCol).setData(newValue); // set data to nodeOne's value + nodeTwo' value
		}
	}
	public void subtractCells(int fNRow, int fNCol, int sNRow, int sNCol, int dNRow, int dNCol){
		GNode nodeOne = moveCurrentPositionTo(head, 0, 0, fNRow, fNCol);
		GNode nodeTwo = moveCurrentPositionTo(nodeOne, fNRow, fNCol, sNRow, sNCol);
		Value newValue = nodeOne.getData().minus(nodeTwo.getData());
		if(!newValue.getTag().equals("INVALID")){										  // check both value are double
			moveCurrentPositionTo(nodeTwo, sNRow, sNCol, dNRow, dNCol).setData(newValue); // set data to nodeOne's value + nodeTwo' value
		}
	}
	public void multiplyCells(int fNRow, int fNCol, int sNRow, int sNCol, int dNRow, int dNCol){
		GNode nodeOne = moveCurrentPositionTo(head, 0, 0, fNRow, fNCol);
		GNode nodeTwo = moveCurrentPositionTo(nodeOne, fNRow, fNCol, sNRow, sNCol);
		Value newValue = nodeOne.getData().star(nodeTwo.getData());
		if(!newValue.getTag().equals("INVALID")){										  // check both value are double
			moveCurrentPositionTo(nodeTwo, sNRow, sNCol, dNRow, dNCol).setData(newValue); // set data to nodeOne's value + nodeTwo' value
		}
	}
	public void divideCells(int fNRow, int fNCol, int sNRow, int sNCol, int dNRow, int dNCol){
		GNode nodeOne = moveCurrentPositionTo(head, 0, 0, fNRow, fNCol);
		GNode nodeTwo = moveCurrentPositionTo(nodeOne, fNRow, fNCol, sNRow, sNCol);
		Value newValue = nodeOne.getData().slash(nodeTwo.getData());
		if(!newValue.getTag().equals("INVALID")){										  // check both value are double
			moveCurrentPositionTo(nodeTwo, sNRow, sNCol, dNRow, dNCol).setData(newValue); // set data to nodeOne's value + nodeTwo' value
		}
	}
	public void addRows(int fRow, int sRow, int tRow){
		for(int i = 0; i < numCol; i++){
			addCells(fRow, i, sRow, i, tRow, i);					// use add One cell method and iterate all the right cells
		}
	}
	public void subtractRows(int fRow, int sRow, int tRow){
		for(int i = 0; i < numCol; i++){
			subtractCells(fRow, i, sRow, i, tRow, i);				
		}
	}
	public void multiplyRows(int fRow, int sRow, int tRow){
		for(int i = 0; i < numCol; i++){
			multiplyCells(fRow, i, sRow, i, tRow, i);				
		}
	}
	public void divideRows(int fRow, int sRow, int tRow){
		for(int i = 0; i < numCol; i++){
			divideCells(fRow, i, sRow, i, tRow, i);					
		}
	}
	public void addColumns(int fCol, int sCol, int tCol){	
		for(int i = 0; i < numRow; i++){
			addCells(i, fCol, i, sCol, i, tCol);					// use add One cell method and iterate all the right cells
		}
	}
	public void subtractColumns(int fCol, int sCol, int tCol){
		for(int i = 0; i < numRow; i++){
			subtractCells(i, fCol, i, sCol, i, tCol);					
		}
	}
	public void multiplyColumns(int fCol, int sCol, int tCol){
		for(int i = 0; i < numRow; i++){
			multiplyCells(i, fCol, i, sCol, i, tCol);					
		}
	}
	public void divideColumns(int fCol, int sCol, int tCol){
		for(int i = 0; i < numRow; i++){
			divideCells(i, fCol, i, sCol, i, tCol);					
		}
	}
	public void insertRow(int newRow){
		GNode newRowCurrent = new GNode(new Value());				// this is the first node in row
		GNode current;												// pointer for previous row
		if(newRow == 0) { 											// want to insert before row 0
			newRow = numRow;										// set newRow to be the row after last row in current grid
			current = moveFromHeadToPosition(newRow - 1,0);			// move to previous row, newRow = 3, 3-1 = 2
			this.head = newRowCurrent;								// set head to be the first one in new row
		}
		else
			current = moveFromHeadToPosition(newRow - 1,0);			// move to previous row, newRow = 3, 3-1 = 2
		numRow = numRow + 1;										// add one row
		for(int i = 0; i < numCol; i++){
			if(i != 0){												// if not first node then create new node, and link right
				GNode newNode = new GNode(new Value());				// because if first node there is no right node yet
				newRowCurrent.right = newNode;
				newRowCurrent = newNode;
			}
			newRowCurrent.down = current.down;						// link down
			current.down = newRowCurrent;
			current = current.right;
			if( i == (numCol - 1) ){ newRowCurrent.right = current.down; } // if at the last col, then link last to fist
		}
	}
	public void insetColumns(int newCol){
		GNode newColCurrent = new GNode(new Value());				// this is the first node in column
		GNode current;												// pointer for previous column
		if(newCol == 0){											// want to insert before column 0
			newCol = numCol;										// set newCol to be the col after last col in current grid
			current = moveFromHeadToPosition(0, newCol - 1);		// move to previous column, 
			this.head = newColCurrent;								// set head to be the first one in new column
		}
		else
			current = moveFromHeadToPosition(0,newCol - 1);			// move to last column, -1 because start from 0
		numCol = numCol + 1;										// add one column
		for(int i = 0; i < numRow; i++){
			if(i != 0){												// if not first node then create new node, and link down
				GNode newNode = new GNode(new Value());				// because if first node there is no down node yet
				newColCurrent.down = newNode;
				newColCurrent = newNode;
			}
			newColCurrent.right = current.right;					// link right
			current.right = newColCurrent;
			current = current.down;
			if( i == (numRow - 1) ){ newColCurrent.down = current.down.right; } // if at the last col, then link last to fist
		}
	}
	public void deleteRow(int num){
		GNode current;
		if(num != 0)
			current = moveFromHeadToPosition(num - 1, 0);			// move to previous row 
		else{
			current = moveFromHeadToPosition(numRow - 1, 0);		// if delete row 0, move to last row 
			head = head.down;										// we delete head row, so change head to first one in next row
		}
		for(int i = 0; i < numCol; i++){
			current.down = current.down.down;						// bypass
			current = current.right;
		}
		numRow = numRow - 1;
	}
	public void deleteColumn(int num){
		GNode current; 
		if(num != 0)
			current = moveFromHeadToPosition(0, num - 1);			// move to previous col
		else{
			current = moveFromHeadToPosition(0, numCol -1);
			head = head.right;										// same as delr but change to right 
		}
		for(int i = 0; i < numRow; i++){
			current.right = current.right.right;					// bypass
			current = current.down;
		}
		numCol = numCol - 1;
	}
	private GNode moveFromHeadToPosition(int tRow, int tCol){			// move from head and return target node
		int countRow, countCol;
		GNode current = head;
		for(countCol = 0; countCol < tCol; countCol++){ current = current.right; }
		for(countRow = 0; countRow < tRow; countRow++){ current = current.down; }
		return current;	
	}
	// move from current to and return target node
	private GNode moveCurrentPositionTo(GNode current, int fRow, int fCol, int toRow, int toCol){
		current = moveRight(current,toCol - fCol);
		current = moveDown(current, toRow - fRow);
		return current;
	}
	private GNode moveRight(GNode current, int num){				// move to right number of times
		if(num < 0) { num = num + numCol; }							// target position is Left side
		for(int i = 0; i < num; i++) { current = current.right; }
		return current;
	}
	private GNode moveDown(GNode current, int num){					// move to right number of times
		if(num < 0) { num = num + numRow; }							// target position is Top side
		for(int i = 0; i < num; i++) { current = current.down; }
		return current;
	}
	private class GNode{			// inner GNode class
		Value data;					// Value field
		GNode right;				// Right Pointer to link nodes into rows
		GNode down;					// Down Pointer to link nodes into column
		public GNode(Value data){	// GNode ctor taking Value as argument
			this.data = data;
		}
		public Value getData() { return this.data; }
		public void setData( Value d){ this.data = d; }
	}
}
