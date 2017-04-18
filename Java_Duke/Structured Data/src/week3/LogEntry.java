package week3;

import java.util.Date;

public class LogEntry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public LogEntry(String line){
		//not implemented at present
	}
	
	public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int bytesReturned) {
		super();
		this.ipAddress = ipAddress;
		this.accessTime = accessTime;
		this.request = request;
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
	}

	
	private String ipAddress;
	private Date accessTime;
	private String request;
	private int statusCode;
	private int bytesReturned;
	
	public String 
	
	public String getIpAddress() {
		return ipAddress;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public String getRequest() {
		return request;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public int getBytesReturned() {
		return bytesReturned;
	}
	
	
	
}
