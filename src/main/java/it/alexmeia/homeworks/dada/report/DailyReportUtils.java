package it.alexmeia.homeworks.dada.report;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import it.alexmeia.homeworks.dada.report.bo.LogData;
import it.alexmeia.homeworks.dada.report.bo.ReportRow;

public class DailyReportUtils {

	public static void writeReport(File logFile, File reportFile) throws IOException {

		List<ReportRow> reportRowList = buildReportList(logFile);
		CSVWriter writer = new CSVWriter(new FileWriter(reportFile), ';');

		for (ReportRow row : reportRowList) {
			writer.writeNext(row.toStringArray());
		}

		writer.close();
	}

	static List<ReportRow> buildReportList(File logFile) throws IOException {

		int totalBytes = 0;
		int totalRequests = 0;

		Map<String, LogData> logMap = new HashMap<String, LogData>();
		CSVReader reader = new CSVReader(new FileReader(logFile), ';');

		String[] row;

		while ((row = reader.readNext()) != null) {

			if ("200".equals(row[2])) {

				totalRequests++;

				String ip = row[3];
				int bytes = Integer.parseInt(row[1]);

				totalBytes += bytes;

				if (logMap.containsKey(ip)) {
					LogData logData = logMap.get(ip);
					logMap.get(ip).setBytes(logData.getBytes() + bytes);
					logMap.get(ip).setRequests(logData.getRequests() + 1);
				} else {
					logMap.put(ip, new LogData(bytes, 1));
				}
			}
		}

		reader.close();

		return buildOrderedListWithPercentage(logMap, totalBytes, totalRequests);
	}

	static List<ReportRow> buildOrderedListWithPercentage(Map<String, LogData> logMap, int totalBytes,
			int totalRequests) {

		List<ReportRow> reportList = new ArrayList<ReportRow>();

		for (Map.Entry<String, LogData> entry : logMap.entrySet()) {

			LogData logData = entry.getValue();

			ReportRow reportRow = new ReportRow();
			reportRow.setIp(entry.getKey());
			reportRow.setRequests(logData.getRequests());
			reportRow.setRequestPercentage((logData.getRequests() * 100.0d / totalRequests));
			reportRow.setBytes(logData.getBytes());
			reportRow.setBytesPercentage((logData.getBytes() * 100.0d / totalBytes));

			reportList.add(reportRow);
		}

		Collections.sort(reportList, new Comparator<ReportRow>() {
			public int compare(ReportRow o1, ReportRow o2) {
				return o1.getRequests() > o2.getRequests() ? -1 : 1;
			}
		});

		return reportList;
	}

}
