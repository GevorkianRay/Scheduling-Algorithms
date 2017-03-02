import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Tester {
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;
	private static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		  writer = new PrintWriter("output.txt", "UTF-8");
		//testFCFS();
		//testSJF();
		//testSRT();
		//testRR();
		//testHPFNP();
		testHPFP();
		writer.close();
	}
	
	public static void testFCFS() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing FCFS.");
		FCFS fcfs = new FCFS(generateList(0));
		writer.write(fcfs.getAverages() + "\n" + fcfs.getOut());
	
		
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(30));
		writer.write(fcfs.getAverages() + "\n" + fcfs.getOut());
		
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(60));
		writer.write(fcfs.getAverages() + "\n" + fcfs.getOut());
		
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(90));
		writer.write(fcfs.getAverages() + "\n" + fcfs.getOut());
		
		printTable(fcfs.getProcesses());
		fcfs = new FCFS(generateList(120));
		writer.write(fcfs.getAverages() + "\n" + fcfs.getOut());
		
		printTable(fcfs.getProcesses());
	}
	
	public static void testSJF() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing SJF.");
		SJF sjf = new SJF(generateList(0));
		writer.write(sjf.getAverages() + "\n" + sjf.getOut());
	
		
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(30));
		writer.write(sjf.getAverages() + "\n" + sjf.getOut());
		
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(60));
		writer.write(sjf.getAverages() + "\n" + sjf.getOut());
		
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(90));
		writer.write(sjf.getAverages() + "\n" + sjf.getOut());
		
		printTable(sjf.getProcesses());
		sjf = new SJF(generateList(120));
		writer.write(sjf.getAverages() + "\n" + sjf.getOut());
		
		printTable(sjf.getProcesses());
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

	public static void printTable(List<Process> processes) throws FileNotFoundException, UnsupportedEncodingException 
	{
		
		 

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
		//System.out.println(table);
		
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