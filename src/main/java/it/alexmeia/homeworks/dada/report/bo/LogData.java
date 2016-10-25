package it.alexmeia.homeworks.dada.report.bo;

public class LogData {
	
	private int bytes;
	private int requests;
	
	public LogData(int bytes, int requests) {
		super();
		this.bytes = bytes;
		this.requests = requests;
	}
	
	public int getBytes() {
		return bytes;
	}
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	public int getRequests() {
		return requests;
	}
	public void setRequests(int requests) {
		this.requests = requests;
	}
}
