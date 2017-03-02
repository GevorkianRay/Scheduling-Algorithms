import java.util.*;

public class SRT {
	private ArrayList<Process> processes;
	private ArrayList<Process> queue;
	private Process shortest;
	private ArrayList<Process> completed;
	private static int QUANTA_MAX = 99;
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;

	public static void main(String[] args) {
		ArrayList<Process> processes = new ArrayList<Process>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process process = new Process("P" + i, i);
			processes.add(process);
		}
		Process.sortListByArrivalTime(processes);

		SRT srt = new SRT(processes);
		String table = "";
		for (Process process : srt.getProcesses()) {
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

	public SRT(ArrayList<Process> processes) {
		this.processes = processes;
		this.queue = new ArrayList<Process>();
		this.shortest = null;
		this.completed = new ArrayList<Process>();
		run();
	}

	public ArrayList<Process> getProcesses() {
		return this.completed;
	}

	private void run() {
		int quanta = 0;
		double totalTurnaroundTime = 0;
		double totalWaitTime = 0;
		double totalResponseTime = 0;
		double processesFinished = 0;

		while (quanta < QUANTA_MAX || !queue.isEmpty()) {
			// Check the current time quanta, and add processes that arrive at the current time to the queue.
			for (Process process : processes) {
				if (process.getArrivalTime() <= quanta) {
					queue.add(process);
					// processes.remove(process);
				}
			}
			processes.removeAll(queue);

			// Preemptive code block.
			if (!queue.isEmpty()) {
				// Get the process in the queue with the lowest remaining execution time.
				shortest = queue.get(0);
				for (Process process : queue) {
					if (process.getExecutionTimeRemaining() < shortest.getExecutionTimeRemaining()) {
						shortest = process;
					}
				}
				queue.remove(shortest);
				
				// If the current shortest process has not started yet, start it.
				if (shortest.getStartExecutionTime() < 0 && quanta < QUANTA_MAX) {
					shortest.setStartExecutionTime(quanta);
				}
				
				// If the process has started
				if (shortest.getStartExecutionTime() > -1) {
					// Represent it in the timeline
					System.out.print("[" + shortest.getName() + "]");
					// Decrement the execution time remaining
					shortest.decrementExecutionTimeRemaining();
					// Move the time splice
					quanta++;

					// If the shortest process is done (execution time remaining == 0)
					if (shortest.getExecutionTimeRemaining() <= 0) {
						// Set its end time at the current quanta
						shortest.setEndTime(quanta);
						// Add the finished process to the completed list.
						completed.add(shortest);

						processesFinished++;
						totalTurnaroundTime += shortest.calculateTurnaroundTime();
						totalWaitTime += shortest.calculateWaitTime();
						totalResponseTime += shortest.calculateResponseTime();
					} else {
						// The process is not done, add it back into the queue.
						queue.add(shortest);
					}
				}
			} else {
				System.out.print("[*]");
				quanta++;
			}
		}

		System.out.println("\n");
		System.out.println("Average turnaround time: " + totalTurnaroundTime / processesFinished);
		System.out.println("Average wait time: " + totalWaitTime / processesFinished);
		System.out.println("Average response time: " + totalResponseTime / processesFinished);
		System.out.println("Throughput: " + processesFinished / quanta + " processes completed per quanta.");
		System.out.println();
	}
}