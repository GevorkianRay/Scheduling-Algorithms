import java.util.ArrayList;

public class FCFS {
	private ArrayList<Process> processes;
	private ArrayList<Process> completed;
	private static int QUANTA_MAX = 100;
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;

	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
		this.completed = new ArrayList<Process>();
		execute();
	}

	public ArrayList<Process> getProcesses() {
		return this.completed;
	}

	public static void main(String[] args) {
		ArrayList<Process> processes = new ArrayList<Process>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process process = new Process("P" + i, i);
			processes.add(process);
		}
		Process.sortListByArrivalTime(processes);

		FCFS fcfs = new FCFS(processes);

		String table = "";
		for (Process process : fcfs.getProcesses()) {
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

	// Wait time = startExecutionTime - arrivalTime

	private void execute() {
		int quanta = 0;
		double totalTurnaroundTime = 0;
		double totalWaitTime = 0;
		double totalResponseTime = 0;
		double processesFinished = 0;

		for (Process process : processes) {
			if (quanta < QUANTA_MAX) {
				while (process.getExecutionTimeRemaining() > 0) {
					if (process.getArrivalTime() <= quanta) {
						if (process.getExecutionTimeRemaining() == process.getGivenExecutionTime()) {
							process.setStartExecutionTime(quanta);
						}
						process.decrementExecutionTimeRemaining();
						quanta++;
						System.out.print("[" + process.getName() + "]");
					} else {
						quanta++;
						System.out.print("[*]");
					}
				}
				
				completed.add(process);
				processesFinished++;
				process.setEndTime(quanta);
				totalTurnaroundTime += process.calculateTurnaroundTime();
				totalWaitTime += process.calculateWaitTime();
				totalResponseTime += process.calculateResponseTime();
			} else {
				break;
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