package less13.modul;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import less13.FileParsingTest;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ReaderFromZIPFile {
    ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void zipTestPDF () throws Exception{
        try(
                InputStream resource = cl.getResourceAsStream("Testdata.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ){
            ZipEntry entry;


            while (((entry = zis.getNextEntry()) != null)){

                if (entry.getName().endsWith(".pdf")){break;}
            }

            PDF pdfFile = new PDF(zis);

            assertThat(pdfFile.text).contains("Операция");

        }
    }

    @Test
    void zipTestXLS () throws Exception{
        try(
                InputStream resource = cl.getResourceAsStream("Testdata.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ){
            ZipEntry entry;


            while (((entry = zis.getNextEntry()) != null)){

                if (entry.getName().contains("PETSTORE.xls")){break;}
            }

            XLS xlsFile = new XLS(zis);

            assertThat(xlsFile.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).isEqualTo("dog");

        }
    }
    @Test
    void zipTestCSV () throws Exception{
        try(
                InputStream resource = cl.getResourceAsStream("Testdata.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ){
            ZipEntry entry;


            while (((entry = zis.getNextEntry()) != null)){

                if (entry.getName().contains("CSVDATA.csv")){break;}
            }

            CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();

                    assertThat(content.getFirst()).contains("New ages become hard!");



        }
    }
}
