import java.util.Scanner;

public class Driver 
{
	public static void main(String[]args)
	{
		Scanner keyboard = new Scanner(System.in);
		boolean continues = true;
		String input;
		Grid myGrid = new Grid();
		String menu = 
		"\nOperations\n" +
		"	display           dis           assign cell       as\n" +
        "	fill              f             number            n\n" +
        "	add cells         a             subtract cells    s\n" +
        "	multiply cells    m             divide cells      d\n" +
        "	add rows          ar            subtract rows     sr\n" +
        "	multiply rows     mr            divide rows       dr\n" +
        "	add columns       ac            subtract columns  sc\n" +
        "	multiply columns  mc            divide columns    dc\n" +
        "	insert row        ir            insert column     ic\n" +
        "	delete row        delr          delete column     delc\n" +
        "	quit              q           \n" +
        "-> ";
		do
		{
			int fRow, fCol, toRow, toCol, destRow, destCol;
			String wValue;
			System.out.print(menu);
			input = keyboard.next();
			switch(input)
			{
				case "dis":											//	display
					myGrid.display();
					break;
				case "as":											// assign cell
					System.out.print("to row:  ");
					toRow = keyboard.nextInt();
					System.out.print("to column:  ");
					toCol = keyboard.nextInt();
					System.out.print("with value:  ");
					keyboard.nextLine();
					wValue = keyboard.nextLine();
					if( Validation.rowColRange(myGrid, toRow, toCol,0,0,0,0) ) // check input validation
						myGrid.assignCell(toRow, toCol, wValue);
					break;
				case "f":											//	fill
					System.out.print("from row:  ");
					fRow = keyboard.nextInt();
					System.out.print("from column:  ");
					fCol = keyboard.nextInt();
					System.out.print("to row:  ");
					toRow = keyboard.nextInt();
					System.out.print("to column:  ");
					toCol = keyboard.nextInt();
					System.out.print("with value:  ");
					keyboard.nextLine();
					wValue = keyboard.nextLine();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,0,0)		// check input validation
							&& Validation.fillNumberRange(myGrid, fRow, fCol, toRow, toCol)) 
						myGrid.fill(fRow, fCol, toRow, toCol, wValue);
					break;
				case "n":											// number
					System.out.print("from row:  ");
					fRow = keyboard.nextInt();
					System.out.print("from column:  ");
					fCol = keyboard.nextInt();
					System.out.print("to row:  ");
					toRow = keyboard.nextInt();
					System.out.print("to column:  ");
					toCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,0,0)		// check input validation
							&& Validation.fillNumberRange(myGrid, fRow, fCol, toRow, toCol))
						myGrid.number(fRow, fCol, toRow, toCol);
					break;
				case "a":											// add cells
					System.out.print("first node row:  ");
					fRow = keyboard.nextInt();
					System.out.print("first node column:  ");
					fCol = keyboard.nextInt();
					System.out.print("second node row:  ");
					toRow = keyboard.nextInt();
					System.out.print("second node column:  ");
					toCol = keyboard.nextInt();
					System.out.print("destination node row:  ");
					destRow = keyboard.nextInt();
					System.out.print("destination node column:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,destRow,destCol) ) // check input validation
						myGrid.addCells(fRow, fCol, toRow, toCol, destRow, destCol);
					break;
				case "s":											// subtract cells
					System.out.print("first node row:  ");
					fRow = keyboard.nextInt();
					System.out.print("first node column:  ");
					fCol = keyboard.nextInt();
					System.out.print("second node row:  ");
					toRow = keyboard.nextInt();
					System.out.print("second node column:  ");
					toCol = keyboard.nextInt();
					System.out.print("destination node row:  ");
					destRow = keyboard.nextInt();
					System.out.print("destination node column:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,destRow,destCol) ) // check input validation
						myGrid.subtractCells(fRow, fCol, toRow, toCol, destRow, destCol);
					break;
				case "m":											// multiply cells
					System.out.print("first node row:  ");
					fRow = keyboard.nextInt();
					System.out.print("first node column:  ");
					fCol = keyboard.nextInt();
					System.out.print("second node row:  ");
					toRow = keyboard.nextInt();
					System.out.print("second node column:  ");
					toCol = keyboard.nextInt();
					System.out.print("destination node row:  ");
					destRow = keyboard.nextInt();
					System.out.print("destination node column:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,destRow,destCol) ) // check input validation
						myGrid.multiplyCells(fRow, fCol, toRow, toCol, destRow, destCol);
					break;
				case "d":											// divide cells
					System.out.print("first node row:  ");
					fRow = keyboard.nextInt();
					System.out.print("first node column:  ");
					fCol = keyboard.nextInt();
					System.out.print("second node row:  ");
					toRow = keyboard.nextInt();
					System.out.print("second node column:  ");
					toCol = keyboard.nextInt();
					System.out.print("destination node row:  ");
					destRow = keyboard.nextInt();
					System.out.print("destination node column:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, fCol, toRow, toCol,destRow,destCol) ) // check input validation
						myGrid.divideCells(fRow, fCol, toRow, toCol, destRow, destCol);
					break;
				case "ar":											// add rows
					System.out.print("First row:  ");
					fRow = keyboard.nextInt();
					System.out.print("Second row:  ");
					toRow = keyboard.nextInt();
					System.out.print("Target row:  ");
					destRow = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, 0, toRow, 0,destRow,0) ) // check input validation
						myGrid.addRows(fRow, toRow, destRow);
					break;
				case "sr":											// subtract rows
					System.out.print("First row:  ");
					fRow = keyboard.nextInt();
					System.out.print("Second row:  ");
					toRow = keyboard.nextInt();
					System.out.print("Target row:  ");
					destRow = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, 0, toRow, 0,destRow,0) ) // check input validation
						myGrid.subtractRows(fRow, toRow, destRow);
					break;
				case "mr":											// multiply rows
					System.out.print("First row:  ");
					fRow = keyboard.nextInt();
					System.out.print("Second row:  ");
					toRow = keyboard.nextInt();
					System.out.print("Target row:  ");
					destRow = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, 0, toRow, 0,destRow,0) ) // check input validation
						myGrid.multiplyRows(fRow, toRow, destRow);
					break;
				case "dr":											// divide rows
					System.out.print("First row:  ");
					fRow = keyboard.nextInt();
					System.out.print("Second row:  ");
					toRow = keyboard.nextInt();
					System.out.print("Target row:  ");
					destRow = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, 0, toRow, 0,destRow,0) ) // check input validation
						myGrid.divideRows(fRow, toRow, destRow);
					break;
				case "ac":											// add columns
					System.out.print("First col:  ");
					fCol = keyboard.nextInt();
					System.out.print("Second col:  ");
					toCol = keyboard.nextInt();
					System.out.print("Target cow:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,0, fCol, 0, toCol,0,destCol) ) // check input validation
						myGrid.addColumns(fCol, toCol, destCol);
					break;
				case "sc":											// subtract columns
					System.out.print("First col:  ");
					fCol = keyboard.nextInt();
					System.out.print("Second col:  ");
					toCol = keyboard.nextInt();
					System.out.print("Target cow:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,0, fCol, 0, toCol,0,destCol) ) // check input validation
						myGrid.subtractColumns(fCol, toCol, destCol);
					break;
				case "mc":											// multiply columns
					System.out.print("First col:  ");
					fCol = keyboard.nextInt();
					System.out.print("Second col:  ");
					toCol = keyboard.nextInt();
					System.out.print("Target cow:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,0, fCol, 0, toCol,0,destCol) ) // check input validation
						myGrid.multiplyColumns(fCol, toCol, destCol);
					break;
				case "dc":											// divide columns
					System.out.print("First col:  ");
					fCol = keyboard.nextInt();
					System.out.print("Second col:  ");
					toCol = keyboard.nextInt();
					System.out.print("Target cow:  ");
					destCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,0, fCol, 0, toCol,0,destCol) ) // check input validation
						myGrid.divideColumns(fCol, toCol, destCol);
					break;
				case "ir":
					System.out.print("row number:  ");
					fRow = keyboard.nextInt();
					if( Validation.insertRowColRange(myGrid, fRow, 0))
						myGrid.insertRow(fRow);
					break;
				case "ic":
					System.out.print("column number:  ");
					fCol = keyboard.nextInt();
					if( Validation.insertRowColRange(myGrid, 0, fCol))
						myGrid.insetColumns(fCol);
					break;
				case "delr":
					System.out.print("row number:  ");
					fRow = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,fRow, 0, 0, 0,0,0) // check input validation
							&& myGrid.getNumRows() != 1) 
					myGrid.deleteRow(fRow);
					break;
				case "delc":
					System.out.print("column number:  ");
					fCol = keyboard.nextInt();
					if( Validation.rowColRange(myGrid,0, fCol, 0, 0,0,0)  // check input validation
							&& myGrid.getNumCols() != 1)
					myGrid.deleteColumn(fCol);
					break;
				case "q":											// quit
					continues = false;
					break;
				default:
					System.out.println("\n<<<< no operation matches " + input + " input!! >>>>");
					break;
			}
		}while(continues);
	}
}
