import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()
WebUI.callTestCase(findTestCase('SCN_001_TC_003_Login'), [:], FailureHandling.STOP_ON_FAILURE)

Object excelRowNumber = findTestData('datadownload').getRowNumbers()

for (int rowCounter =1; rowCounter <= excelRowNumber; rowCounter++) {
	
	'Mengambil nama dari excel'
	String getName = findTestData('datadownload').getValue('Nama',rowCounter)
	'Mengamil job dari excel'
	String getJob = findTestData('datadownload').getValue('Job', rowCounter)
	'Mengambil point dari id di excel'
	String getPoint = findTestData('datadownload').getValue('No',rowCounter)
	

	'Menginput nama'
	WebUI.setText(findTestObject('dashboard/input_name'),getName+'_'+getJob)
	println(getName+"_"+getJob+" " + "point = " +getPoint)
	'Menginput point'
	WebUI.setText(findTestObject('dashboard/input_poin'),getPoint)
	'tekan Submit'
	WebUI.click(findTestObject('dashboard/button_submit'))
	
	WebUI.delay(2);
	
}

println('selesai')


