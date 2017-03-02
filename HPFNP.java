import java.util.*;

public class HPFNP {
	private ArrayList<Process> processes;
	private ArrayList<Process> completed;
	private Queue<Process> q1;
	private Queue<Process> q2;
	private Queue<Process> q3;
	private Queue<Process> q4;
	private Process current;
	private static int QUANTA_MAX = 100;
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;
	
	
	private int quanta;
	private double totalTurnaroundTime;
	private double totalWaitTime;
	private double totalResponseTime;
	private double processesFinished;
	private String out;

	public static void main(String[] args) {
		ArrayList<Process> processes = new ArrayList<Process>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process process = new Process("P" + i, i);
			processes.add(process);
		}
		Process.sortListByArrivalTime(processes);
		HPFNP hpfnp = new HPFNP(processes);

		String table = "";
		for (Process process : hpfnp.getProcesses()) {
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

	public HPFNP(ArrayList<Process> processes) {
		this.processes = processes;
		this.completed = new ArrayList<Process>();
		q1 = new LinkedList<Process>();
		q2 = new LinkedList<Process>();
		q3 = new LinkedList<Process>();
		q4 = new LinkedList<Process>();
		out ="";
		execute();
	}

	private void execute() {
		quanta = 0;
		totalTurnaroundTime = 0;
		totalResponseTime = 0;
		processesFinished = 0;
		totalWaitTime = 0;

		// Check the current time quanta, and add processes based on priority that arrive at the current time to the q's
		while (quanta < QUANTA_MAX) {
			for (Process process : processes) {
				if (process.getArrivalTime() <= quanta) {
					if (process.getPriority() == 1) {
						q1.add(process);
					} else if (process.getPriority() == 2) {
						q2.add(process);
					} else if (process.getPriority() == 3) {
						q3.add(process);
					} else if (process.getPriority() == 4) {
						q4.add(process);
					} else {
						System.exit(1); // getting a non-existant priority.
					}
				}
			}
			processes.removeAll(q1);
			processes.removeAll(q2);
			processes.removeAll(q3);
			processes.removeAll(q4);

			//non preemptive block of code
			// Get a process from the q's from priority 1 to 4
			if (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty() || !q4.isEmpty()) {
				if (!q1.isEmpty()) {
					current = q1.remove();
				} else if (!q2.isEmpty()) {
					current = q2.remove();
				} else if (!q3.isEmpty()) {
					current = q3.remove();
				} else if (!q4.isEmpty()) {
					current = q4.remove();
				} else {
					System.exit(0);
				}
				// If the current shortest process has not started yet, start it.
				current.setStartExecutionTime(quanta);
				//once 1 quanta is passed, decrements the quanta of that process and adds to time line 
				while (current.getExecutionTimeRemaining() > 0) {
					current.decrementExecutionTimeRemaining();
					quanta++;
					out = out +("[" + current.getName() + "]");
				}
				current.setEndTime(quanta);
				//adds unfinished but decremented process back to its priority queue
				completed.add(current);

				processesFinished++;
				totalTurnaroundTime += current.calculateTurnaroundTime();
				totalWaitTime += current.calculateWaitTime();
				totalResponseTime += current.calculateResponseTime();
			} else {
				out =out +("[*]");
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