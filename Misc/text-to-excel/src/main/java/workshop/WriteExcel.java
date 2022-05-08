package workshop;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Subodh Kumar
 */
public class WriteExcel {

    private static final String EMP_TEXT_FILE = "src/main/resources/input/employee.txt";
    private static final String EMP_EXCEL_FILE = "src/main/resources/output/employee.xlsx";

    public static void main(String[] args) {

        writeExcelUsingWorkbook();
    }

    private static void writeExcelUsingWorkbook() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        try ( BufferedReader br = new BufferedReader(new FileReader(EMP_TEXT_FILE))) {
            Files.deleteIfExists(Paths.get(EMP_EXCEL_FILE));
            System.out.println("previous file deleted");
            Thread.sleep(2000);

            int rowIndex = 0;
            String line;

            while ((line = br.readLine()) != null) {
                Row row = sheet.createRow(rowIndex);
                String[] tokens = line.split("[|]");
                for (int i = 0; i < tokens.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(tokens[i]);
                }
                rowIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try ( FileOutputStream output = new FileOutputStream(EMP_EXCEL_FILE)) {
            workbook.write(output);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file created");
    }
}
