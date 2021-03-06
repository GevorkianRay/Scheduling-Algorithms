import java.util.*;

public class SJF {
	private ArrayList<Process> processes;
	private ArrayList<Process> queue;
	private Process shortest;
	private ArrayList<Process> completed;
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

		SJF sjf = new SJF(processes);

		String table = "";
		for (Process process : sjf.getProcesses()) {
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

	public SJF(ArrayList<Process> processes) {
		this.processes = processes;
		this.queue = new ArrayList<Process>();
		this.shortest = null;
		this.completed = new ArrayList<Process>();
		out="";
		run();
	}

	public ArrayList<Process> getProcesses() {
		return this.completed;
	}

	private void run() {
		quanta = 0;
		totalTurnaroundTime = 0;
		totalWaitTime = 0;
		totalResponseTime = 0;
		processesFinished = 0;
		
		while (quanta < QUANTA_MAX) {
			// Check the current time quanta, and add processes that arrive at the current time to the queue.
			for (Process process : processes) {
				if (process.getArrivalTime() <= quanta) {
					queue.add(process);
					//processes.remove(process);
				}
			}
			processes.removeAll(queue);

			//preemptive block code
			if (!queue.isEmpty()) {
				// Get the process in the queue with the lowest remaining execution time.
				shortest = queue.get(0);
				for (Process process : queue) {
					if (process.getGivenExecutionTime() < shortest.getGivenExecutionTime()) {
						shortest = process;
					}
				}

				shortest.setStartExecutionTime(quanta);
				queue.remove(shortest);

				// Run till death.
				while (shortest.getExecutionTimeRemaining() > 0) {
					shortest.decrementExecutionTimeRemaining();
					quanta++;
					//time line representation
					out = out+ ("[" + shortest.getName() + "]");
				}
				shortest.setEndTime(quanta);
				completed.add(shortest);
				
				processesFinished++;
				totalTurnaroundTime += shortest.calculateTurnaroundTime();
				totalWaitTime += shortest.calculateWaitTime();
				totalResponseTime += shortest.calculateResponseTime();
			} else {
				out = out+ ("[*]");
				//move time splice
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