package ExtentReport;


import java.text.SimpleDateFormat;
import java.util.Date;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;




public class ExtentReportClass {
	
	protected static ExtentTest logger;
	protected static ExtentReports report;
	

	
	public static void CloseReport() {
		report.endTest(logger);
		report.flush();
	}


	/*
	 * name of the Method: CreateReport BriedDescriotion : This will create a empty
	 * Extent Report. Arguments : No Arguments Created By : Automation Team created
	 * Date : 01/16/2021
	 */
	public static void CreateReport() {
		String fileName = new SimpleDateFormat("'Rest_Country_Report_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "Report/" + fileName;
		report = new ExtentReports(path);
	}
	
	
}
