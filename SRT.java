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
			Process process = new Process("P" + i, i + 30);
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
			if (quanta < QUANTA_MAX) {
				for (Process process : processes) {
					if (process.getArrivalTime() <= quanta) {
						queue.add(process);
						// processes.remove(process);
					}
				}
				processes.removeAll(queue);
			}

			if (!queue.isEmpty()) {
				shortest = queue.get(0);
				for(Process process : queue) {
					if (queue.size)
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