import java.util.ArrayList;

public class FCFS {
	private ArrayList<Process> processes;
	private ArrayList<Process> completed;
	private static int QUANTA_MAX = 100;
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;

	
	private int quanta;
	private double totalTurnaroundTime;
	private double totalWaitTime;
	private double totalResponseTime;
	private double processesFinished;
	private String out;

	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
		this.completed = new ArrayList<Process>();
		out="";
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
		//System.out.println(table);
	}

	// Wait time = startExecutionTime - arrivalTime

	private void execute() {
		quanta = 0;
		totalTurnaroundTime = 0;
		totalWaitTime = 0;
		totalResponseTime = 0;
		processesFinished = 0;

		for (Process process : processes) {
			if (quanta < QUANTA_MAX) {
				while (process.getExecutionTimeRemaining() > 0) {
					if (process.getArrivalTime() <= quanta) {
						if (process.getExecutionTimeRemaining() == process.getGivenExecutionTime()) {
							process.setStartExecutionTime(quanta);
						}
						process.decrementExecutionTimeRemaining();
						quanta++;
						out = out+("[" + process.getName() + "]");
					} else {
						quanta++;
						out = out+("[*]");
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