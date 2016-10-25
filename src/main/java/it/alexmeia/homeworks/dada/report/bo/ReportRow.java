package it.alexmeia.homeworks.dada.report.bo;

public class ReportRow {

	private String ip;
	private int requests;
	private double requestPercentage;
	private int bytes;
	private double bytesPercentage;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public double getRequestPercentage() {
		return requestPercentage;
	}

	public void setRequestPercentage(double requestPercentage) {
		this.requestPercentage = requestPercentage;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getBytesPercentage() {
		return bytesPercentage;
	}

	public void setBytesPercentage(double bytesPercentage) {
		this.bytesPercentage = bytesPercentage;
	}

	public String[] toStringArray() {

		String[] values = new String[5];
		values[0] = this.ip;
		values[1] = String.valueOf(this.requests);
		values[2] = String.valueOf(this.requestPercentage);
		values[3] = String.valueOf(this.bytes);
		values[4] = String.valueOf(this.bytesPercentage);

		return values;
	}

}
