package com.example.plan.xmlEntityControlller;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DocxReplaceController {

    @Autowired
    private xmlRepo xmlEntityRepository; // Replace xmlEntityRepository with your actual repository

    @PostMapping("/generate-docx-files")
    public ResponseEntity<List<String>> generateDocxFiles() {
        try {
            // Fetch all data from the database
            List<xmlEntity> allxmlEntity = xmlEntityRepository.findAll();

            // Create a list to store the file paths of the generated DOCX files
            List<String> filePaths = generateDocxFiles(allxmlEntity);

            return ResponseEntity
                    .ok()
                    .body(filePaths);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private List<String> generateDocxFiles(List<xmlEntity> xmlEntityList) throws IOException {
        // Create a list to store the file paths of the generated DOCX files
        List<String> filePaths = new ArrayList<>();

        // Iterate over the data fetched from the database
        for (xmlEntity xmlEntity : xmlEntityList) {
            try {
                // Read the DOCX template file content
                byte[] dotTemplateContent = Files.readAllBytes(Paths.get("C:\\TestXML\\af_plan_provision_summary_document_section_1_09.2020.dot"));

                // Replace XML tags in the DOCX file
                byte[] modifiedDocument = replaceXmlTagsInDocx(dotTemplateContent, xmlEntity);

                // Save the modified DOCX file
                String filePath = saveModifiedDocxFile(modifiedDocument, xmlEntity.getEvId());

                // Add the file path to the list
                filePaths.add(filePath);
            } catch (IOException e) {
                // Handle exceptions for the current xmlEntity if needed
                e.printStackTrace();
            }
        }

        return filePaths;
    }

    private byte[] replaceXmlTagsInDocx(byte[] dotContent, xmlEntity xmlEntity) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(dotContent);
             HWPFDocument document = new HWPFDocument(inputStream);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            replaceTags(document, xmlEntity);
            document.write(outputStream);

            return outputStream.toByteArray();
        }
    }

    private void replaceTags(HWPFDocument document, xmlEntity planInfo) {
        Range range = document.getRange();

        // Define your tag replacements
        Map<String, String> tagReplacements = Map.of(
                "<ssmPlanNumber>", planInfo.getEvId(),
                "<ssmPlanName>", planInfo.getPlanName()
                // Add more tag replacements as needed
                
        );

        // Iterate through the tag replacements
        for (Map.Entry<String, String> entry : tagReplacements.entrySet()) {
            String tag = entry.getKey();
            String replacement = entry.getValue();

            // Debugging: Print the tag and replacement value
            System.out.println("Replacing tag: " + tag);
            System.out.println("With value: " + replacement);

            // Replace the tag in the document
            range.replaceText(tag, replacement);
        }
    }
 
//        document.getParagraphs().forEach(paragraph -> {
//            // Iterate through all runs in the paragraph
//            for (XWPFRun run : paragraph.getRuns()) {
//                // Get the text content of the run
//                String text = run.getText(0);
//
//                // Debugging: Print the text and replacement value
//                System.out.println("Original Text: " + text);
//                System.out.println("Replacement Value: " + xmlEntity.getEvId());
//
//                // Replace the <ev_id></ev_id> tag with the actual evId from xmlEntity
//                if (text != null && text.contains("<plan_name></plan_name>")) {
//                    run.setText(text.replace("<plan_name></plan_name>", xmlEntity.getPlanName()), 0);
//                }
//                else if (text.contains("<ev_id></ev_id>")) {
//                    // Replace {planName} with the actual planName from xmlEntity
//                    run.setText(text.replace("<ev_id></ev_id>", xmlEntity.getEvId()), 0);
//                }
//
//                // Debugging: Print the modified text after replacement
//                System.out.println("Modified Text: " + run.getText(0));
//            }
//        });
//    }
    private String saveModifiedDocxFile(byte[] modifiedDocxContent, String evId) throws IOException {
        String filePath = "C:\\dummy xml\\" + evId + "_modified.doc";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(modifiedDocxContent);
        }
        return filePath;
    }
    }