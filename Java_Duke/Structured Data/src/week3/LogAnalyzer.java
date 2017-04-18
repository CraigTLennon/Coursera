package week3;

import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.FileResource;

public class LogAnalyzer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	private ArrayList<LogEntry> records;
	
	
	
	public LogAnalyzer(){
		records=new ArrayList<LogEntry>();
	}
	
	public HashMap<String,Integer> countVisitsPerIP(){
		HashMap<String,Integer> counts=new HashMap<String,Integer>();
		for(LogEntry rec:records){
			String ip=rec.getIpAddress();
			if(counts.containsKey(ip)){
				counts.put(ip, counts.get(ip)+1);
			}else{
				counts.put(ip,1);
			}
		}
		return counts;
		
	}
	public HashMap<String,ArrayList<String>> ipsForDays(){
		HashMap<String,ArrayList<String>> ipByDay= new HashMap<String,ArrayList<String>>(); //day then array of ips for that day
		ArrayList<String> uniqueDays=new ArrayList<String>();
		for(LogEntry rec : records){
			String nDay=rec.getAccessTime().toString().substring(4, 10);
			if(!uniqueDays.contains(nDay)){
				uniqueDays.add(nDay);
			}
		}
		for(String day : uniqueDays){
		
		ipByDay.put(day, uniqueIPVisitsOnDay(day));
		}
		
		return ipByDay;	
	}
	
	public ArrayList<String> ipsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayToIPs,String day){
		HashMap<String,Integer> ipCount= new HashMap<String,Integer>();
		for(LogEntry rec: records){
			String ips = rec.getIpAddress();
			String recDay=rec.getAccessTime().toString().substring(4, 10);
			if(ipCount.containsKey(ips) && recDay.equals(day)){
				ipCount.put(ips, ipCount.get(ips)+1);
			}else if(recDay.equals(day)){
				ipCount.put(ips, 1);
			}
		}
		//System.out.println(ipCount);
		return ipsMostVisits(ipCount);
		
		
	}
	
	public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPs){ 
			int maxIPNum=0;
			String maxDay="";
			for(String day: dayIPs.keySet()){
				if(maxIPNum<dayIPs.get(day).size()){
					maxIPNum=dayIPs.get(day).size();
					maxDay=day;
				}
			}
		
		return maxDay;
	}
	
	public ArrayList<String> ipsMostVisits(HashMap<String,Integer> counts){
		ArrayList<String> ips= new ArrayList<String>();
		int maxNum=mostNumberVisitsByIP(counts);
		for(String ip : counts.keySet()){
			if(counts.get(ip)==maxNum){
				ips.add(ip);
			}
		}
		return ips;
	}
	
	public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
		int maxVisits =0;
		for(int ct : counts.values()){
			if(ct>maxVisits){
				maxVisits=ct;
			}
		}
		
		return maxVisits;
	}
	
	public void readFile(String filename){
		records.clear();
		FileResource fr= new FileResource(filename);
		for(String line : fr.lines()){
			//WebLogParser W = new WebLogParser(); //changed b/c/ "static field should be accessed in static way" 
			//which means call the method on the class itself and not an instance
			LogEntry L=WebLogParser.parseEntry(line);
			records.add(L);
		}
	}
	
	public int countUniqueIP(){
		ArrayList<String> ips=new ArrayList<String>();
		for(LogEntry le : records){
			String ip = le.getIpAddress();
			if(!ips.contains(ip)){
				ips.add(ip);
			}
		}
		return ips.size();
	}

		
	public ArrayList<String> uniqueIPVisitsOnDay(String someday){
		//someday is MMM as letters and DD as day of month no year Dec 05 or Apr 22
		ArrayList<String> ips=new ArrayList<String>();
		for(LogEntry le : records){
			String ip = le.getIpAddress();
			String date=le.getAccessTime().toString().substring(4, 10);
			if( someday.equals(date) && !ips.contains(ip)){
				ips.add(ip);
			}
		}
		return ips;
	}
	
	public int countUniqueIPsInRange(int low, int high){
		ArrayList<String> ips=new ArrayList<String>();
		for(LogEntry rec : records){
			if(rec.getStatusCode()>=low && rec.getStatusCode()<=high && !ips.contains(rec.getIpAddress())){
				ips.add(rec.getIpAddress());
			}
		}
		return ips.size();
	}
	
	public void printAllHigherThanNum(int num){
		//examine records and print all logentries with status higher than num
		for(LogEntry rec : records){
			if(rec.getStatusCode()>num){
				System.out.println(rec);
			}
		}
	}
	
	public void printAll(){
		for(LogEntry le : records){
			System.out.println(le);
		}
	}
	
	

}
