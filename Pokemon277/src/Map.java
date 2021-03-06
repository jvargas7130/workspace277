
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program creates a class map that stores a map in an array and then uses
 * a point to locate the start and continued location of the player
 * 
 * @author jesus Vargas
 *
 *
 */
public class Map {
	/**
	 * map with hidden icons
	 */
	private char[][] map;

	/**
	 * True map
	 */
	private boolean[][] revealed;

	/**
	 * Map constructor
	 */
	public Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];
	}

	/**
	 * Generate Area method stores a map in an array
	 * 
	 * @param areaNum area where player is located
	 *           
	 */
	public void generateArea(int areaNum) {

		String num = "Area" + areaNum + ".txt";

		try {
			int counter = 0;

			Scanner read = new Scanner(new File(num));
			do {

				String line = read.nextLine();

				for (int i = 0; i < map[0].length; i++) {
					boolean b = false;
					char c = line.charAt(i * 2);
					if (c == 'c') {
						b = true;
					}

					revealed[i][counter] = b;
					map[i][counter] = c;

				}

				counter++;
			} while (read.hasNext());

		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		}

	}

	/**
	 * Char at loc returns the location of player
	 * 
	 * @param p  passes in a point
	 *          
	 * @return char location
	 */
	public char getCharAtLoc(Point p) {
		char c = ' ';
		int x = (int) p.getX();
		int y = (int) p.getY();
		try {
			c = map[x][y];
			reveal(p);
		} catch (ArrayIndexOutOfBoundsException ob) {
			c = 'k';
		}

		return c;
	}

	/**
	 * Display map method displays the map
	 * 
	 * @param p passes in point object
	 *           
	 */
	public void displayMap(Point p) {

		int x = (int) p.getX();
		int y = (int) p.getY();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				if (y == i && x == j) {
					System.out.print("*" + " ");
				} else if (revealed[j][i] == false) {
					System.out.print("X" + " ");
				} else {

					System.out.print(map[j][i] + " ");
				}

			}
			System.out.println();
		}

	}

	/**
	 * Find Start Location method finds the location for point
	 * 
	 * @return a point object
	 */
	public Point findStartLocation() {
		Point p = new Point(0, 0);
		int startX = 0;
		int startY = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 's') {
					startX = i;
					startY = j;
				}
			}
		}

		p.setLocation(startX, startY);

		int x = (int) p.getX();
		int y = (int) p.getY();

		p = new Point(x, y);
		reveal(p);
		return p;

	}

	/**
	 * Reveal method Reveals point
	 * 
	 * @param p passes in point
	 *           
	 */
	public void reveal(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		revealed[x][y] = true;
	}

	/**
	 * remote oponent at location
	 * 
	 * @param p
	 */
	public void removeOppAtLoc(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		map[x][y] = 'n';
	}
}
