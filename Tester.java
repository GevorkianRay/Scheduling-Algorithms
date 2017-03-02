import java.util.*;

public class Tester {
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;

	public static void main(String[] args) {
		testFCFS();
		testSJF();
	}
	
	public static void testFCFS() {
		System.out.println("//- Testing FCFS.");
		FCFS fcfs = new FCFS(generateList(0));
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(30));
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(60));
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(90));
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(120));
		printTable(fcfs.getProcesses());
	}
	
	public static void testSJF() {
		System.out.println("//- Testing SJF.");
		SJF sjf = new SJF(generateList(0));
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(30));
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(60));
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(90));
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(120));
		printTable(sjf.getProcesses());
	}

	public static void printTable(List<Process> processes) {
		String table = "";
		for (Process process : processes) {
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

	public static ArrayList<Process> generateList(int seed) {
		ArrayList<Process> processes = new ArrayList<Process>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process process = new Process("P" + i, i + seed);
			processes.add(process);
		}
		Process.sortListByArrivalTime(processes);
		return processes;
	}
}