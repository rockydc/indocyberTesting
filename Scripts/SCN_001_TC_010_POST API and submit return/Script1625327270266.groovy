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
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.Assertion as Keys
// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()
'Login '
WebUI.callTestCase(findTestCase('SCN_001_TC_003_Login'), [:], FailureHandling.STOP_ON_FAILURE)

'Melakukan pengecekan jumlah row yang ada di excel'
Object excelRowNumber = findTestData('datadownload').getRowNumbers()


'Melakukan POST API User dari data excel dan menginputkanya ke dalam website'
println(PostApiUser(excelRowNumber))

WebUI.closeBrowser()

def addData(String name, String point) {
	
    WebUI.setText(findTestObject('dashboard/input_name'), name)

    WebUI.setText(findTestObject('dashboard/input_poin'), point)

    WebUI.click(findTestObject('dashboard/button_submit'))


    WebUI.delay(2)
	
	String namaLengkap = name+" "+point;
	return namaLengkap
}

def PostApiUser(def rowData) {
    List<String> dataList = new ArrayList()

    JsonSlurper parser = new JsonSlurper()
	'Melakukan perulangan menambah data dengan response Post API'
	
    for (int rowCounter = 1; rowCounter <= rowData; rowCounter++) {
        'Mengambil data nama dari excel'
        String getName = findTestData('datadownload').getValue('Nama', rowCounter)
		"Mengambil data  job dari excel"
        String getJob = findTestData('datadownload').getValue('Job', rowCounter)
		"Mengambil data point dari excel"
        String getPoint = findTestData('datadownload').getValue('No', rowCounter)

        'post to API'

        'Send request and caputre the response'
        def response = WS.sendRequest(findTestObject('API/PostUserAPI', [('name') : getName, ('job') : getJob]))

		
        def responseAfterParsing = parser.parseText(response.getResponseBodyContent())

        println(response.getStatusCode())

        println(((('name = ' + getName) + ' , ') + 'Job = ') + getJob)

        def responseName = responseAfterParsing.name

        def responseId = responseAfterParsing.id

        println((responseName + ' , ') + responseId)

        println('type data name = ' + responseName.getClass().getSimpleName())

        println('type data point = ' + responseId.getClass().getSimpleName())

        'add Data to website '
        println('=========================add data to website=========================');
        dataList.add(addData(responseName, responseId))
    }
    
    return dataList
}

