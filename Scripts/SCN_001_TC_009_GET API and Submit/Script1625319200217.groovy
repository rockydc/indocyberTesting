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
import groovy.json.internal.Value as Value
import groovy.sql.DataSet as DataSet
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()
'Login'
WebUI.callTestCase(findTestCase('SCN_001_TC_003_Login'), [:], FailureHandling.STOP_ON_FAILURE)

'Menampilkan response API '
def responseData = WS.sendRequest(findTestObject('API/UserAPI'))

def slurper = new JsonSlurper()
'Melakukan parsing dengan slurper'
def result = slurper.parseText(responseData.getResponseBodyContent())

'Mengambil response yang sudah di parsing'
def value = result.data

'Menghitung jumlah data'
def valueLength = value.size()

'Melakukan perulangan input data kedalam website sesuai dengan data yang di excel'
for (def dataUser : value) {
	'melakukan input data '
    String name = (dataUser.first_name + ' ') + dataUser.last_name

    String point = dataUser.id
	'mengisikan nama'
    WebUI.setText(findTestObject('dashboard/input_name'), name)
	'mengisikan point'
    WebUI.setText(findTestObject('dashboard/input_poin'), point)
	
    println('nama = ' + name)

    println('poin = ' + point)
	
	'klik submit'
    WebUI.click(findTestObject('dashboard/button_submit'))


	'web delay'
    WebUI.delay(2)
}

println('============= SELESAI ===============')

'menutup browser'
WebUI.closeBrowser()

