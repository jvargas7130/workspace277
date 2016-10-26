import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Jukebox program provides functionality to display the list of songs, display
 * current song , add a new song to the list , Mark complete - removes task form
 * list, remove and readd to list postpone next task and reads in the files.
 * 
 * @author Jesus Vargas
 *
 */
public class TasklistTest {
	public static void main(String[] args) {
		menu();

	}

	public static void readInFile(String fileName, ArrayList<Job> task) {

		// Reads in Files
		try {
			Scanner read = new Scanner(new File(fileName));
			do {
				String line = read.nextLine();
				String[] tokens = line.split(",");
				task.add(new Job(tokens[0], tokens[1]));
			} while (read.hasNext());
			read.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}

	}

	public static void menu() {
		ArrayList<Job> task = new ArrayList<Job>();

		// read in file for songs
		readInFile("taskList.txt", task);

		// write file for songs
		writeSongsToFile("taskList.txt", task);

		boolean done = false;
		while (!done) {

			System.out.println("1. Display the list of tasks");
			System.out.println("2. Display current task.");
			System.out.println("3. Add a new item to the task list");
			System.out.println("4. Mark Complete");
			System.out.println("5. Postpone next task");
			System.out.println("6. Quit");
			int choose = CheckInput.checkInt(1, 5);

			switch (choose) {
			//1. Display the list of tasks
			case 1:
				break;
				//2. Display current task.
			case 2:
				break;
				//3. Add a new item to the task list
			case 3:
				break;
				//4. Mark Complete
			case 4:
				break;
				//5. Postpone next task
			case 5:
				break;
				//6. Quit
			case 6:
				done = true;
				break;

			default:
				break;

			}

		}
	}

	public static void writeSongsToFile(String fileName, ArrayList<Job> task) {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			for (Job p : task) {

				writer.println(p.toString());
			}
			writer.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}

}