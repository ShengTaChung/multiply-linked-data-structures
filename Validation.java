
public class Validation
{
	public static boolean rowColRange(Grid myGrid, int rowOne, int colOne, int rowTwo, int colTwo, int rowThree, int colThree)	
	{
		if( rowOne < myGrid.getNumRows() && colOne < myGrid.getNumCols() &&				// 0 to rows - 1 AND 0 to col - 1
				rowTwo < myGrid.getNumRows() && colTwo < myGrid.getNumCols() &&
				rowThree < myGrid.getNumRows() && colThree < myGrid.getNumCols() &&
				rowOne >= 0 && colOne >= 0 && rowTwo >= 0 && colTwo >= 0 && rowThree >= 0 && colThree >= 0) 
			return true;
		else
		{
			System.out.println("\n<<< Invalid Range >>>");
			return false;
		}
	}
	public static boolean fillNumberRange(Grid myGrid, int fromRow, int fromCol, int toRow, int toCol)
	{
		if( (fromRow <= toRow) && (fromCol <= toCol) )									//Same or greater
			return true;
		else																			// cannot follow
		{
			System.out.println("\n<<< Invalid Range >>>");
			return false;
		}
	}
	public static boolean insertRowColRange(Grid myGrid, int row, int col)				// no less than 0 or >numRow/Col
	{
		if( ( row >= 0 && row <= myGrid.getNumRows() ) && ( col >= 0 && col <= myGrid.getNumCols()) )
			return true;
		else
		{
			System.out.println("\n<<< Invalid Insert Range >>>");
			return false;
		}
	}
}
