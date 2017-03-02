import java.util.*;

public class RR {
	private ArrayList<Process> processes;
	private ArrayList<Process> queue;
	private ArrayList<Process> completed;
	private Process current;
	private static int QUANTA_MAX = 99;
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;

	
	private int quanta;
	private double totalTurnaroundTime;
	private double totalWaitTime;
	private double totalResponseTime;
	private double processesFinished;
	private String out;
	
	public RR(ArrayList<Process> processes) {
		this.processes = processes;
		this.queue = new ArrayList<Process>();
		this.completed = new ArrayList<Process>();
		out="";
		execute();
	}

	public static void main(String[] args) {
		ArrayList<Process> processes = new ArrayList<Process>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process process = new Process("P" + i, i);
			processes.add(process);
		}
		Process.sortListByArrivalTime(processes);

		RR rr = new RR(processes);

		String table = "";
		for (Process process : rr.getProcesses()) {
			table += "[Process: " + String.format("%3s", process.getName()) + ", Arrival time: "
					+ String.format("%3d", process.getArrivalTime()) + ", Run time: "
					+ String.format("%3d", process.getGivenExecutionTime()) + ", Priority: " + process.getPriority()
					+ ", End time: " + String.format("%3d", process.getEndTime()) + ", Time started: "
					+ String.format("%3d", process.getStartExecutionTime()) + ", Turnaround time: "
					+ String.format("%3d", process.calculateTurnaroundTime()) + ", Wait time: "
					+ String.format("%3d", process.calculateWaitTime()) + ", Response time: "
					+ String.format("%3d", process.calculateResponseTime()) + "]\n";
		}
		System.out.println(table);
	}

	public ArrayList<Process> getProcesses() {
		return this.completed;
	}

	public void execute() {
		quanta = 0;
		totalTurnaroundTime = 0;
		totalWaitTime = 0;
		totalResponseTime = 0;
		processesFinished = 0;

		while (quanta < QUANTA_MAX || !queue.isEmpty()) {
			if (quanta < QUANTA_MAX) {
				// Check the current time quanta, and add processes that arrive at the current time to the queue.
				for (Process process : processes) {
					if (process.getArrivalTime() <= quanta) {
						queue.add(process);
						// processes.remove(process);
					}
				}
				processes.removeAll(queue);
			}
			
			// Preemptive code block.
			if (!queue.isEmpty()) {
				// Get the process in the queue with the lowest remaining execution time.
				current = queue.remove(0);
				// If the current shortest process has not started yet, start it.
				if (current.getStartExecutionTime() < 0 && quanta < QUANTA_MAX) {
					current.setStartExecutionTime(quanta);
				}
				
				// If the process has started
				if (current.getStartExecutionTime() > -1) {
					// Represent it in the timeline
					out =out + ("[" + current.getName() + "]");
					// Decrement the execution time remaining
					current.decrementExecutionTimeRemaining();
					// Move the time splice
					quanta++;

					// If the shortest process is done (execution time remaining == 0)
					if (current.getExecutionTimeRemaining() <= 0) {
						// Set its end time at the current quanta
						current.setEndTime(quanta);
						// Add the finished process to the completed list.
						completed.add(current);

						processesFinished++;
						totalTurnaroundTime += current.calculateTurnaroundTime();
						totalWaitTime += current.calculateWaitTime();
						totalResponseTime += current.calculateResponseTime();
					} else {
						// The process is not done, add it back into the queue.
						queue.add(current);
					}
				}
			} else {
				out = out + ("[*]");
				quanta++;
			}
		}
		
	}
	
	public String getAverages()
	{
		String averages = "\n" +"Averages turnaround time: " + totalTurnaroundTime / processesFinished +"\n"
						+ "Average wait time: " + totalWaitTime / processesFinished +"\n" +
						"Average response time: " + totalResponseTime / processesFinished +"\n"
						+ "Throughput: " + processesFinished / quanta + " processes completed per quanta." +"\n";
		/*System.out.println("\n");
		System.out.println("Average turnaround time: " + totalTurnaroundTime / processesFinished);
		System.out.println("Average wait time: " + totalWaitTime / processesFinished);
		System.out.println("Average response time: " + totalResponseTime / processesFinished);
		System.out.println("Throughput: " + processesFinished / quanta + " processes completed per quanta.");
		System.out.println();*/
		return  averages;
	}
	
	public String getOut()
	{
		out = out +"\n";
		return out;
	}
}