package it.alexmeia.homeworks.dada.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVReader;

import it.alexmeia.homeworks.dada.report.DailyReportUtils;
import it.alexmeia.homeworks.dada.report.bo.LogData;
import it.alexmeia.homeworks.dada.report.bo.ReportRow;

public class DailyReportUtilsTest {

	private static final String LOGFILE_PATH = "logfiles/requests.log";
	private static final String REPORT_PATH = "reports/ipaddr.csv";

	File logFile;
	File reportFile;

	@Before
	public void createFiles() {
		logFile = new File(getClass().getClassLoader().getResource(LOGFILE_PATH).getFile());
		reportFile = new File(getClass().getClassLoader().getResource(REPORT_PATH).getFile());
	}

	@Test
	public void testWriteReport() throws IOException {

		DailyReportUtils.writeReport(logFile, reportFile);

		CSVReader reader = new CSVReader(new FileReader(reportFile), ';');
		List<String[]> reportRows = reader.readAll();

		assertTrue("Report file is not empty", reportRows != null);
		assertTrue("Report line number as expected", reportRows.size() == 4);
		assertTrue("IP address with more request as espected.", reportRows.get(0)[0].equals("10.184.128.227"));
		assertTrue("Total bytes of first IP as espected.", reportRows.get(0)[3].equals("1892"));

		reader.close();
	}

	@Test
	public void testBuildReportList() throws IOException {

		List<ReportRow> reportRowList = DailyReportUtils.buildReportList(logFile);

		ReportRow firstRow = reportRowList.get(0);
		ReportRow rowWithOneRequest = null;
		ReportRow rowWithTwoRequests = null;
		ReportRow rowWithStatus404 = null;

		for (ReportRow row : reportRowList) {

			switch (row.getIp()) {

			case "10.191.255.229":
				rowWithOneRequest = row;
				break;

			case "10.190.128.226":
				rowWithTwoRequests = row;
				break;

			case "10.185.255.229":
				rowWithStatus404 = row;
				break;

			default:
				break;
			}
		}

		assertEquals(4, reportRowList.size());
		assertFalse("Request with http status different from OK will be written in report file.",
				rowWithStatus404 != null);
		assertTrue("IP 10.191.255.229 made one request with status OK.", rowWithOneRequest.getRequests() == 1);
		assertTrue("Maximum requests per IP are 3.", firstRow.getRequests() == 3);
		assertTrue("Total bytes for IP with more requests are 1892.", firstRow.getBytes() == 1892);
		assertTrue("Request percentage for IP with 2 request is 25%",
				rowWithTwoRequests.getRequestPercentage() == 25.0d);
	}

	@Test
	public void testBuildOrderedListWithPercentage() {

		int totalBytes = 5000;
		int totalRequests = 10;
		Map<String, LogData> logMap = new HashMap<String, LogData>();
		logMap.put("10.0.0.1", new LogData(2500, 6));
		logMap.put("10.0.0.2", new LogData(2500, 4));

		List<ReportRow> reportRowList = DailyReportUtils.buildOrderedListWithPercentage(logMap, totalBytes,
				totalRequests);

		assertEquals(2, reportRowList.size());
		assertTrue("List is ordered by requests number desc.", reportRowList.get(0).getIp().equals("10.0.0.1"));
		assertTrue("Both IP sent same percentage of byte.",	reportRowList.get(0).getBytesPercentage() == reportRowList.get(1).getBytesPercentage());

	}

}
