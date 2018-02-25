package net.burakuyar.relatify.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.writers.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 2/25/2018.
 */
public class JSONExport {
    private SpreadsheetExtractionAlgorithm extractor = new SpreadsheetExtractionAlgorithm();
    public JsonArray PDFToJSON(InputStream is) throws Exception{
        StringWriter sw = new StringWriter();
        PDDocument pdfDocument = PDDocument.load(is);
        PageIterator iterator = getPageIterator(pdfDocument);
        List<Table> tables = new ArrayList<>();
        while(iterator.hasNext()){
            Page page = iterator.next();
            tables.addAll(extractor.extract(page));
        }
        (new JSONWriter()).write(sw, tables);
        pdfDocument.close();
        sw.close();
        return new Gson().fromJson(sw.toString(), JsonArray.class);
    }
    private PageIterator getPageIterator(PDDocument pdfDocument) throws IOException {
        ObjectExtractor extractor = new ObjectExtractor(pdfDocument);
        return extractor.extract();
    }
}
