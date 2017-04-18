package week3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogEntryTest {

	public static void main(String[] args) {
		LogEntryTest LT=new LogEntryTest();
		LogEntry L = new LogEntry("1.2.3.4",new Date(),"example request",200,404);
		LT.testLogEntry(L);
		LogAnalyzer LA = new LogAnalyzer();
		LT.testLA(LA);
		
	}
	
	public void testLA(LogAnalyzer LA){
		String shortLog="C:/Users/cLennon/Documents/GitHub/Coursera/Java_Duke/Structured Data/src/data/UniqueIPData/weblog-short_log";
		LA.readFile(shortLog);
		LA.printAll();
		
		System.out.println("There are "+LA.countUniqueIP()+" unique ips");
		LA.printAllHigherThanNum(300);
		ArrayList<String> ips=LA.uniqueIPVisitsOnDay("Sep 14");
		System.out.println(ips);
		int ipsInRange=LA.countUniqueIPsInRange(300, 399);
		System.out.println(ipsInRange+" unique ips in the specified range");
		HashMap<String,Integer> counts=LA.countVisitsPerIP();
//		for(String ip :counts.keySet()){
//			System.out.println("IP "+ip+" visted "+counts.get(ip)+" times.");
//		}
		System.out.println(counts);
		System.out.println(LA.mostNumberVisitsByIP(counts));
		System.out.println(LA.ipsMostVisits(counts));
		System.out.println(LA.ipsForDays());
		HashMap<String,ArrayList<String>> dayIPs=LA.ipsForDays();
		System.out.println(LA.dayWithMostIPVisits(dayIPs));
		
		ArrayList<String> ipsOnDay= LA.ipsWithMostVisitsOnDay(dayIPs, "Sep 30");
		System.out.println(ipsOnDay);
		
	}
	
	public void testLogEntry(LogEntry Le){
		String s=Le.toString();
		System.out.println(s);
		System.out.println(Le);//print uses toString method if defined o/w uses default toString
		
	}

}
