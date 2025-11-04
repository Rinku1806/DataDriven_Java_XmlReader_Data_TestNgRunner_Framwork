package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import util.Xml_Reader;

public class DataUtil extends testbase.BaseTest {

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String testName = m.getName();
		Object[][] data = new Xml_Reader(xmlR).getDataByTestName(testName);

		for (Object[] element : data) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.println(element[j]);
			}
		}
		return data;
	}

}