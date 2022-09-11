package com.infosys.in.misc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;

@RestController
public class MiscController {
	
	@GetMapping("/docToDocx")
	public ResponseEntity<String> convert() {
		String inputFile = "src/main/resources/file-sample_100kB.doc";
		String outputFile = "src/main/resources/file-samplex_100kB.docx";
		
		POIFSFileSystem fs = null;  
        Document document = new Document();

        try {  
            System.out.println("Starting the test");  
            fs = new POIFSFileSystem(new FileInputStream(inputFile));  

            HWPFDocument doc = new HWPFDocument(fs);  
            WordExtractor we = new WordExtractor(doc);  

            OutputStream file = new FileOutputStream(new File(outputFile)); 

            System.out.println("Document testing completed");  
        } catch (Exception e) {  
            System.out.println("Exception during test");  
            e.printStackTrace();  
        } finally {  
            // close the document  
            document.close();  
        }
		
		return new ResponseEntity<>("Ok", HttpStatus.ACCEPTED);
	}
	

}
