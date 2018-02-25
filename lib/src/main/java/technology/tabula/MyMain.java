package technology.tabula;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.cli.ParseException;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.writers.JSONWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 2/23/2018.
 */
public class MyMain {

    public JsonArray PDFToJSON(File pdfFile){
        String str = "[]";
        try {
            str = extractFileInto(pdfFile);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(str, JsonArray.class);
    }
    private String extractFileInto(File pdfFile) throws ParseException {
        StringWriter sw = new StringWriter();
        extractFile(pdfFile, sw);
        try {
            sw.close();
        } catch (IOException e) {
            System.out.println("Error in closing the BufferedWriter" + e);
        }
        return sw.toString();
    }
    private void extractFile(File pdfFile, Appendable outFile) throws ParseException {
        PDDocument pdfDocument = null;
        try {
            pdfDocument =  PDDocument.load(pdfFile);
            PageIterator pageIterator = getPageIterator(pdfDocument);
            List<Table> tables = new ArrayList<>();

            while (pageIterator.hasNext()) {
                Page page = pageIterator.next();
                tables.addAll(basicExtractor.extract(page));
            }
            (new JSONWriter()).write(outFile, tables);
        } catch (IOException e) {
            throw new ParseException(e.getMessage());
        } finally {
            try {
                if (pdfDocument != null) {
                    pdfDocument.close();
                }
            } catch (IOException e) {
                System.out.println("Error in closing pdf document" + e);
            }
        }
    }
    private SpreadsheetExtractionAlgorithm basicExtractor = new SpreadsheetExtractionAlgorithm();
    private PageIterator getPageIterator(PDDocument pdfDocument) throws IOException {
        ObjectExtractor extractor = new ObjectExtractor(pdfDocument);
        return extractor.extract();
    }
}
