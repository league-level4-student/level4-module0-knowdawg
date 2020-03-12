package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Cell startCell = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(startCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unCells = new ArrayList<Cell>();
		
		Cell yAdd = maze.cells[currentCell.getX()][currentCell.getY() + 1];
		Cell ySub = maze.cells[currentCell.getX()][currentCell.getY() - 1];
		Cell xAdd = maze.cells[currentCell.getX() + 1][currentCell.getY()];
		Cell xSub = maze.cells[currentCell.getX() - 1][currentCell.getY()];
		
		if (currentCell.getY() < height && yAdd.hasBeenVisited() == false) {
			unCells.add(yAdd);
		}
		if (currentCell.getY() > 0 && ySub.hasBeenVisited() == false) {
			unCells.add(ySub);
		}
		if (currentCell.getX() < height && xAdd.hasBeenVisited() == false) {
			unCells.add(xAdd);
		}
		if (currentCell.getX() > 0 && xSub.hasBeenVisited() == false) {
			unCells.add(xSub);
		}
		//C. if has unvisited neighbors,
		if (unCells.size() > 0) {
			
			//C1. select one at random.
			Cell newCell = unCells.get(randGen.nextInt(unCells.size()));
			//C2. push it to the stack
			uncheckedCells.push(newCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(newCell);
		} else {
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				
				// D1a. pop a cell from the stack
				
				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}
