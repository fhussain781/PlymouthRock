package utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*This class will be used to read excel file and get values by rows.
 * 
 * 
 * */
public class Excel {

	//Describe file or path
	private static final String FileX = System.getProperty("user.dir") + "//Xcel//data.xlsx";

	public static void main(String[] args) {
		
		System.out.println("Reading file");
		getData(2,4);
	}
	

	
	
	
	//read excel
	public static void ReadExcelFile() {
		
		Map<Integer,List<String>> mapData =  new LinkedHashMap<>();
		List<String> columnData = new ArrayList<>();
		try {
			FileInputStream ReadFile = new FileInputStream(FileX);
			Workbook workbook = new XSSFWorkbook(ReadFile);
			Sheet MyProjectSheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = MyProjectSheet.iterator();
			Row currentrow = iterator.next();
			while (iterator.hasNext()) {
				currentrow = iterator.next();
				Iterator<Cell> cellIterator = currentrow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "---");
						columnData.add(currentCell.getStringCellValue());
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						
					if ((currentCell.getNumericCellValue() == Math.floor(currentCell.getNumericCellValue()) && !Double.isInfinite(currentCell.getNumericCellValue()))) {
							Date javaDate= DateUtil.getJavaDate((double) currentCell.getNumericCellValue());
					        String date =new SimpleDateFormat("MM/dd/yyyy").format(javaDate);
					        columnData.add(date);
						}else {
						System.out.print(currentCell.getNumericCellValue() + "---");
						String numberData = "Number:"+ currentCell.getNumericCellValue();
						columnData.add(numberData.split(":")[1]);}
					} else if (currentCell.getNumericCellValue()== (double) currentCell.getNumericCellValue()){
							
							
						System.out.print(currentCell.getDateCellValue() + "---");
						String numberData = "Number:"+ currentCell.getNumericCellValue();
						columnData.add(numberData.split(":")[1]);
					}
				}
				 mapData.put(currentrow.getRowNum(),columnData);
				System.out.println(workbook.getSheetAt(0).getRow(2).getCell(4));
			}
			System.out.println("File Read!\n");
			
			workbook.close();
			
		

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static String getData(int row, int column) {
		Map<Integer,List<String>> mapData =  new LinkedHashMap<>();
		String columnData=null;
		try {
			FileInputStream ReadFile = new FileInputStream(FileX);
			Workbook workbook = new XSSFWorkbook(ReadFile);
			Sheet MyProjectSheet = workbook.getSheetAt(0);
			Cell currentCell= workbook.getSheetAt(0).getRow(row).getCell(column);
			
			
			if (currentCell.getCellType()==CellType.STRING){
				

				columnData =workbook.getSheetAt(0).getRow(row).getCell(column).toString();
				}else if ((currentCell.getNumericCellValue() == Math.floor(currentCell.getNumericCellValue())
					&& !Double.isInfinite(currentCell.getNumericCellValue()))){
				Date javaDate= DateUtil.getJavaDate((double) currentCell.getNumericCellValue());
		         
		        columnData=new SimpleDateFormat("MM/dd/yyyy").format(javaDate);
		        }else if(currentCell.getDateCellValue()!=null){
			

			columnData =workbook.getSheetAt(0).getRow(row).getCell(column).toString();
			}else if (currentCell.getCellType()==CellType.NUMERIC){
				

				columnData =workbook.getSheetAt(0).getRow(row).getCell(column).toString();
				}
				System.out.println(columnData);
			
			System.out.println("File Read!\n");
			
			workbook.close();
			
		

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columnData;
	
	}
	public static int lastRowNumber() {
		int lastRow =1;
		try {
			FileInputStream ReadFile = new FileInputStream(FileX);
			Workbook workbook = new XSSFWorkbook(ReadFile);
			Sheet MyProjectSheet = workbook.getSheetAt(0);

			 lastRow = workbook.getSheetAt(0).getLastRowNum();
			
						
			}catch(Exception e) {
				
			}
		return lastRow;
	}
}


