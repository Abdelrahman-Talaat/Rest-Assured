import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Data_Driven_Excel {
    public String[][] read_from_excel() throws IOException, InvalidFormatException {
    File myFile=new File("ExcelSheet/paraBank-Excel-Sheet.xlsx");
    XSSFWorkbook wb=new XSSFWorkbook(myFile);
    XSSFSheet sh=wb.getSheet("Sheet1");
    int raw_count=sh.getPhysicalNumberOfRows();
    int column_count=sh.getRow(0).getPhysicalNumberOfCells();
    String[][] myData=new String[raw_count-1][column_count];
    for (int i=1;i<raw_count;i++){
        for (int j=0;j<column_count;j++){
            XSSFRow row= sh.getRow(i);
            myData[i-1][j]=row.getCell(j).toString();

        }

    }
        return myData;
}}
