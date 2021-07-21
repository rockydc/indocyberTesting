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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

// modify WebUI.* keywords which take TestObject as arg0
// so that they call Highlight.on() automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()
WebUI.callTestCase(findTestCase('SCN_001_TC_006_addData_looping'), [:], FailureHandling.OPTIONAL)


List<WebElement> ExpectedValue = ['Susan_1', 'Susan_3', 'Susan_5', 'Susan_7', 'Susan_9', 'Jaka_2', 'Jaka_4', 'Jaka_6', 'Jaka_8'
    , 'Jaka_10']


'Pengulangan penginputan data di website'
for (String name : ExpectedValue) {
	'Mencari nama'
  

    WebDriver driver = DriverFactory.getWebDriver()

    'To Locate Table'
    WebElement Table = driver.findElement(By.xpath('//table/tbody'))

    //To locate rows of table it will capture all the rows available in the table
    println(Table)

    List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

    //'To Calculate no of rows in Table'
	'Menghitung jumlah rows di table'
    int rows_count = rows_table.size()

    println(rows_count)
	
    WebUI.delay(2)

		'Melakukan perulangan sesuai jumlah baris'
        table: for (int i = 0; i < rows_count; i++) {
			'Mencari element property td'
            List<WebElement> Cols = rows_table.get(i).findElements(By.tagName('td'))

            for (int j = 0; j < Cols.size(); j++) {
				'Melakukan pencarian yang namanya sama dengan nama yang ada di list'
				
                if (Cols.get(j).getText().equalsIgnoreCase(name)) {
					'Menghapus nama yang sama yang ada di list'
                    Cols.get(2).findElement(By.tagName('a')).click()

                    println(name)
					
					'Mendapatkan alert'
                    WebUI.acceptAlert()

                    break
					'menghapus data susan dengan point 50'
                }else if(Cols.get(j).getText().equalsIgnoreCase('Susan') && Cols.get(j+1).getText().equalsIgnoreCase('50')) {
					'Menghapus nama yang sama yang ada di list'
					Cols.get(2).findElement(By.tagName('a')).click()

					println(name)
					
					'Mendapatkan alert'
					WebUI.acceptAlert()

					break;
				}else {
					
					break;
				}
            }
        }
    
    
    WebUI.delay(2)
}
'Menutup browser'
WebUI.closeBrowser()

