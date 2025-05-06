package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {

		String path = ".\\testdata\\Opencart_LoginData.xlsx";

		ExcelUtility ef = new ExcelUtility(path);

		int row = ef.getRowCount("Sheet1");
		int cell = ef.getCellCount("Sheet1", 1);

		String data[][] = new String[row][cell];

		for (int i = 1; i <= row; i++) {
			for (int j = 0; j < cell; j++) {
				data[i - 1][j] = ef.getCellData("Sheet1", i, j);
			}
		}

		return data;

	}

}
