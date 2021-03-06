import java.util.ArrayList;
import java.util.Calendar;

/**
 * Job cllass implements  the comparable interface.  It holds toghether a task which consist
 * of taskName and duedate. 
 * 
 * @author Jesus Vargas
 *
 */
public class Job implements Comparable<Job> {
	
	/**
	 * Taskname instant variable of Job
	 */
	private String taskName;
	/**
	 * duedate instant variable of Job
	 */
	private String dueDate;
	
	/**
	 * Job Constructor
	 * 
	 * @param t passes in job string value
	 * @param d passes in duedate string value
	 */
	public Job(String t, String d) {
		taskName = t;
		dueDate = d;
	}
	
	/**
	 * Task Name accessor
	 * 
	 * @return taskName string value
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Due Date accessor
	 * 
	 * @return
	 */
	public String getDueDate() {
		return dueDate;
	}
	
	/**
	 * Month accessor splits the month, stores it 
	 * as an int and returns it
	 * 
	 * @return month integer value 
	 */
	public int getMonth() {

		String dateTimeSplit[] = dueDate.split(" ");
		String date = dateTimeSplit[0];
		String monthDayYear[] = date.split("/");

		int month = Integer.parseInt(monthDayYear[0]);

		return month;
	}
	/**
	 * Day accessor splits the day, stores it 
	 * as an int and returns it
	 * 
	 * @return day integer value
	 */
	public int getDay() {
		String dateTimeSplit[] = dueDate.split(" ");
		String date = dateTimeSplit[0];
		String monthDayYear[] = date.split("/");
		//System.out.println(monthDayYear[1] + " ");
		int day = Integer.parseInt(monthDayYear[1]);

		return day;
	}
	/**
	 * Year accessor splits the year, stores it 
	 * as an int and returns it
	 * 
	 * @return year integer value
	 */
	public int getYear() {
		String dateTimeSplit[] = dueDate.split(" ");
		String date = dateTimeSplit[0];
		String monthDayYear[] = date.split("/");

		int year = Integer.parseInt(monthDayYear[2]);

		return year;
	}
	/**
	 * Hour accessor splits the month, stores it 
	 * as an int and returns it
	 * 
	 * @return hour integer value
	 */
	public int getHour() {
		
		
		
		String dateTimeSplit[] = dueDate.split(" ");
		String date = dateTimeSplit[0];
		String hourMinute[] = dateTimeSplit[1].split(":");
			//System.out.println(hourMinute[0]);
		int hour = Integer.parseInt(hourMinute[0]);

		return hour;
	}
	/**
	 * Minute accessor splits the month, stores it 
	 * as an int and returns it
	 * 
	 * @return minute integer value
	 */
	public int getMinute() {
		String dateTimeSplit[] = dueDate.split(" ");
		String date = dateTimeSplit[0];
		String hourMinute[] = dateTimeSplit[1].split(":");

		int minute = Integer.parseInt(hourMinute[1]);

		return minute;
	}
	
	/**
	 * Tostring method prints the job 
	 * 
	 * @return taskName + "," + dueDate
	 */
	@Override
	public String toString() {

		return taskName + "," + dueDate;

	}

	/**
	 * Comparable method checks to make sure jopb is in order The soonest due
	 * date is compared first and breaks ties by alphabetic order of task name
	 * 
	 * @param passes
	 *            in job object
	 * @return returns negative integer if the two objects are in order, a zero
	 *         if they are equal and a positive integer if they are out of order
	 */

	@Override
	public int compareTo(Job j) {
		int result = 0;

		// check by year
		if (getYear() < j.getYear()) {
			result = -1;
		} else if (getYear() > j.getYear()) {
			result = 1;
		} else if (getYear() == j.getYear()) {

			// check by month
			if (getMonth() < j.getMonth()) {
				result = -1;
			} else if (getMonth() > j.getMonth()) {
				result = 1;
			} else if (getMonth() == j.getMonth()) {

				// check by day
				if (getDay() < j.getDay()) {
					result = -1;
				} else if (getDay() > j.getDay()) {
					result = 1;
				} else if (getDay() == j.getDay()) {

					// check by hour
					if (getHour() < j.getHour()) {
						result = -1;
					} else if (getHour() > j.getHour()) {
						result = 1;
					} else if (getHour() == j.getHour()) {

						// check by minute
						if (getMinute() < j.getMinute()) {
							result = -1;
						} else if (getMinute() > j.getMinute()) {
							result = 1;
						} else if (getMinute() == j.getMinute()) {
							result = taskName.compareToIgnoreCase(j.getTaskName());
						}
					}

				}
			}

		}

		return result;
	}

}
