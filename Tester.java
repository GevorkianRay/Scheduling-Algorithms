import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Tester {
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;
	private static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		writer = new PrintWriter("output.txt", "UTF-8");
		testFCFS();
		testSJF();
		testSRT();
		testRR();
		testHPFNP();
		testHPFP();
		testHPFNP_Aging();
		testHPFP_Aging();
		writer.close();
	}

	public static void testFCFS() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing FCFS.");
		FCFS fcfs1 = new FCFS(generateList(0));
		writer.write(fcfs1.getAverages() + "\n" + fcfs1.getOut());

		printTable(fcfs1.getProcesses());
		FCFS fcfs2 = new FCFS(generateList(30));
		writer.write(fcfs2.getAverages() + "\n" + fcfs2.getOut());

		printTable(fcfs2.getProcesses());
		FCFS fcfs3 = new FCFS(generateList(60));
		writer.write(fcfs3.getAverages() + "\n" + fcfs3.getOut());

		printTable(fcfs3.getProcesses());
		FCFS fcfs4 = new FCFS(generateList(90));
		writer.write(fcfs4.getAverages() + "\n" + fcfs4.getOut());

		printTable(fcfs4.getProcesses());
		FCFS fcfs5 = new FCFS(generateList(120));
		writer.write(fcfs5.getAverages() + "\n" + fcfs5.getOut());

		printTable(fcfs5.getProcesses());
	}

	public static void testSJF() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing SJF.");
		SJF sjf1 = new SJF(generateList(0));
		writer.write(sjf1.getAverages() + "\n" + sjf1.getOut());

		printTable(sjf1.getProcesses());
		SJF sjf2 = new SJF(generateList(30));
		writer.write(sjf2.getAverages() + "\n" + sjf2.getOut());

		printTable(sjf2.getProcesses());
		SJF sjf3 = new SJF(generateList(60));
		writer.write(sjf3.getAverages() + "\n" + sjf3.getOut());

		printTable(sjf3.getProcesses());
		SJF sjf4 = new SJF(generateList(90));
		writer.write(sjf4.getAverages() + "\n" + sjf4.getOut());

		printTable(sjf4.getProcesses());
		SJF sjf5 = new SJF(generateList(120));
		writer.write(sjf5.getAverages() + "\n" + sjf5.getOut());

		printTable(sjf5.getProcesses());
	}


	public static void testSRT() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing SRT.");
		SRT srt = new SRT(generateList(0));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new SRT(generateList(30));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new SRT(generateList(60));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new SRT(generateList(90));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new SRT(generateList(120));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
	}

	public static void testRR() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing RR.");
		RR rr = new RR(generateList(0));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new RR(generateList(30));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new RR(generateList(60));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new RR(generateList(90));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new RR(generateList(120));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
	}

	public static void testHPFNP() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFNP.");
		HPFNP hpfnp = new HPFNP(generateList(0));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP(generateList(30));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP(generateList(60));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP(generateList(90));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP(generateList(120));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
	}

	public static void testHPFP() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFP.");
		HPFP hpfp = new HPFP(generateList(0));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new HPFP(generateList(30));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new HPFP(generateList(60));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new HPFP(generateList(90));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new HPFP(generateList(120));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
	}

	public static void testHPFP_Aging() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFP_Aging.");
		HPFP_Aging hpfnp = new HPFP_Aging(generateList(0));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFP_Aging(generateList(30));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFP_Aging(generateList(60));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFP_Aging(generateList(90));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFP_Aging(generateList(120));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
	}

	public static void testHPFNP_Aging() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFNP_Aging.");
		HPFNP_Aging hpfnp = new HPFNP_Aging(generateList(0));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP_Aging(generateList(30));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP_Aging(generateList(60));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP_Aging(generateList(90));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new HPFNP_Aging(generateList(120));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
	}

	public static void printTable(List<Process> processes) throws FileNotFoundException, UnsupportedEncodingException {
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
		writer.write(table);
		writer.write("\n");
		// System.out.println(table);
	}

	public static double getAverageTurnaroundTime(List<Process> processes1, List<Process> processes2,
												  List<Process> processes3, List<Process> processes4, List<Process> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process p : processes1) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes1.size();
		for (Process p : processes2) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes2.size();
		for (Process p : processes3) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes3.size();
		for (Process p : processes4) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes4.size();
		for (Process p : processes5) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes5.size();
		return average / 5;
	}

	public static double getAverageWaitTime(List<Process> processes1, List<Process> processes2,
											List<Process> processes3, List<Process> processes4, List<Process> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process p : processes1) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes1.size();
		for (Process p : processes2) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes2.size();
		for (Process p : processes3) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes3.size();
		for (Process p : processes4) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes4.size();
		for (Process p : processes5) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes5.size();
		return average;
	}

	public static double getAverageResponseTime(List<Process> processes1, List<Process> processes2,
												List<Process> processes3, List<Process> processes4, List<Process> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process p : processes1) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes1.size();
		for (Process p : processes2) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes2.size();
		for (Process p : processes3) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes3.size();
		for (Process p : processes4) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes4.size();
		for (Process p : processes5) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes5.size();
		return average;
	}

	public static double getAverageThroughput(List<Process> processes1, List<Process> processes2,
											  List<Process> processes3, List<Process> processes4, List<Process> processes5) {
		double p1 = processes1.get(processes1.size() - 1).getEndTime() / processes1.size();
		double p2 = processes2.get(processes2.size() - 1).getEndTime() / processes2.size();
		double p3 = processes3.get(processes3.size() - 1).getEndTime() / processes3.size();
		double p4 = processes4.get(processes4.size() - 1).getEndTime() / processes4.size();
		double p5 = processes5.get(processes5.size() - 1).getEndTime() / processes5.size();
		return (p1 + p2 + p3 + p4 + p5) / 5;
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