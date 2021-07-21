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

// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()
WebUI.callTestCase(findTestCase('SCN_001_TC_005_Add Data and Delete Data'), [:], FailureHandling.OPTIONAL)

int n = 0

int point = 0

for (def index : (1..10)) {
    point = (index * 10)
	
	'Melakukan penambahan data jaka jika iterasi genap'
    if ((index % 2) == 0) {
        WebUI.setText(findTestObject('dashboard/input_name'), 'Jaka_' + index)

        WebUI.setText(findTestObject('dashboard/input_poin'), point.toString())

        WebUI.click(findTestObject('dashboard/button_submit'))

      
    } else {
		'Melakukan penambahan data Susan_ jika iterasi ganjil'
		
		'Menambahkan data nama Susan_n'
        WebUI.setText(findTestObject('dashboard/input_name'), 'Susan_' + index)
		'Menambahkan point = n'
        WebUI.setText(findTestObject('dashboard/input_poin'), point.toString())
		'Klik Submit'
  
    }
}



