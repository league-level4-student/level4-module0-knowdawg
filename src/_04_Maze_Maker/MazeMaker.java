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
		ArrayList<Cell> unCells = getUnvisitedNeighbors(currentCell);

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
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
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

		if (c2.getY() < c1.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}

		if (c2.getY() > c1.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}

		if (c2.getX() > c1.getX()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}

		if (c2.getX() < c1.getX()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		
		ArrayList<Cell> unCells = new ArrayList<Cell>();
		
		if (c.getY() + 1 < height && maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited() == false) {
			unCells.add(maze.getCell(c.getX(), c.getY() + 1));
		}
		if (c.getY() > 0 && maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited() == false) {
			unCells.add(maze.getCell(c.getX(), c.getY() - 1));
		}
		if (c.getX() + 1 < width && maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited() == false) {
			unCells.add(maze.getCell(c.getX() + 1, c.getY()));

		}
		if (c.getX() > 0 && maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited() == false) {
			unCells.add(maze.getCell(c.getX() - 1, c.getY()));
		}
		return unCells;
	}
}




