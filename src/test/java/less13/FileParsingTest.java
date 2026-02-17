package less13;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import less13.modul.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Selenide.*;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class FileParsingTest {

    ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws IOException {
        open("https://docs.junit.org/6.0.3/overview.html");
        File downloadedPDF = $(".pdf-link a").download();
        PDF content = new PDF(downloadedPDF);

        assertThat(content.text).contains("The goal of this document is to provide");
    }
    @Test
    void xlsParseTest() throws IOException {

        try(InputStream resourceAsStream = cl.getResourceAsStream("Sone.xlsx")){
            Assertions.assertNotNull(resourceAsStream);
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(5).getCell(5).getNumericCellValue()).toString().contains("1");
        }

//        open("https://samplelib.com/ru/sample-xls.html");
//        File downloadedPDF = $(".btn").$("a",1).download();
//        PDF content = new PDF(downloadedPDF);
//
//        assertThat(content.text).contains("The goal of this document is to provide");
    }

    @Test
    void csvTest () throws Exception{
    try(
            InputStream resource = cl.getResourceAsStream("kocool.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(resource))
    ){
        List<String[]> content = reader.readAll();
        assertThat(content.get(0)[1]).contains("lesson");
    }
    }

    @Test
    void zipTest () throws Exception{
    try(
            InputStream resource = cl.getResourceAsStream("dfint-installer-win-0.6.0.zip");
            ZipInputStream zis = new ZipInputStream(resource)

    ){
        ZipEntry entry = zis.getNextEntry();

        Assertions.assertNotNull(entry);
        assertThat(entry.getName()).isEqualTo("dfint-installer.exe");

    }
    }

    @Test
    void jsonTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("glossary.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("title").getAsString()).isEqualTo("S");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("flag").getAsBoolean()).isTrue();

        }
    }
    @Test
    void jsonImplementTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("glossary.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {
            Glossary jsonObject = gson.fromJson(reader, Glossary.class);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
            assertThat(jsonObject.glossDiv.flag).isTrue();

        }
    }

}
