package it.alexmeia.homeworks.dada.report;

import java.io.File;
import java.io.IOException;

/**
 * This class provides a main method to test generation of a report file in csv
 * format after reading a log file. Default paths for input and output files are
 * relative. Test files are stored in resources/logfiles and resources/reports
 * folder. Absolute paths for input and output files can be passed as arguments.
 */

public class DailyReportApp {

	private static final String LOGFILE_PATH = "logfiles/requests.log";
	private static final String REPORT_PATH = "reports/ipaddr.csv";

	public static void main(String[] args) {

		File logFile;
		File reportFile;

		try {

			if (args.length != 2) {
				logFile = new File(DailyReportApp.class.getClassLoader().getResource(LOGFILE_PATH).getFile());
				reportFile = new File(DailyReportApp.class.getClassLoader().getResource(REPORT_PATH).getFile());
			} else {
				logFile = new File(args[0]);
				reportFile = new File(args[1]);
			}

			DailyReportUtils.writeReport(logFile, reportFile);
			System.out.println(String.format("Log file %s processed. Report written in %s", logFile.getAbsolutePath(),
					reportFile.getAbsolutePath()));

		} catch (IOException e) {
			// Logs omitted.
			System.out.println(e.getMessage());
		}
	}

}
