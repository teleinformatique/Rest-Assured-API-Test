package api.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testData/userData.xlsx";
        XLUtility xlUtility = new XLUtility(filePath);
        int rowNum = xlUtility.getRowCount("data");
        int colCount = xlUtility.getCellCount("data",1);

        String data[][] = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {

            for (int j = 0; j < colCount; j++) {
                data[i-1][j] = xlUtility.getCellData("data", i, j);
            }

        }

        return data;
    }

    @DataProvider(name = "UserNames")
    public String[] getUsernames() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testData/userData.xlsx";
        XLUtility xlUtility = new XLUtility(filePath);
        int rowNum = xlUtility.getRowCount("data");

        String data[]= new String[rowNum];


            for (int i = 1; i <= rowNum; i++) {
                data[i-1] = xlUtility.getCellData("data", i, 6);
            }

        return data;
    }
}
