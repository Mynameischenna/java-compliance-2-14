//get files from the folder
package com.example.plan.planInfoControlller;
import java.io.IOException;

import java.io.InputStream;

//import java.net.http.HttpHeaders;
import org.springframework.http.HttpHeaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class FileList {
//	@Autowired
//	private JavaMailSender mailsend;
	private static final String UPLOAD_DIR = "C:\\TestXML";
	
	@GetMapping("/files")
    public ResponseEntity<List<String>> getFiles() {
        try {
            // List all files in the directory
            List<String> files = Arrays.stream(Paths.get(UPLOAD_DIR).toFile().list())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
//	@GetMapping("/{filename}")
//	public static void openFile(@PathVariable String filePath) {
//        try {
//            // Convert the file path to a URI
//            URI uri = Paths.get(filePath).toUri();
//
//            // Open the file with the default system application
//            Desktop.getDesktop().browse(uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//	@GetMapping("/{filename}")
//	public ResponseEntity<UrlResource> downloadFile(@PathVariable String filename) {
//	    try {
//	        // Load file as UrlResource
//	        Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
//	        UrlResource urlResource = new UrlResource(filePath.toUri());
//
//	        // Check if the file exists
//	        if (urlResource.exists()) {
//	            return ResponseEntity.ok()
//	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + urlResource.getFilename() + "\"")
//	                    .body(urlResource);
//	        } else {
//	            return ResponseEntity.status(404).body(null);
//	        }
//	    } catch (IOException e) {
//	        return ResponseEntity.status(500).body(null);
//	    }
//	}
	
	

	// ... (other imports)

	@GetMapping("/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {
            // Load file content as bytes
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            byte[] fileContent = Files.readAllBytes(filePath);

            // Determine the media type based on the file extension
            MediaType mediaType = determineMediaType(filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private MediaType determineMediaType(String filename) {
        String fileExtension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        switch (fileExtension) {
        	case "xml":
        		return MediaType.APPLICATION_ATOM_XML;
        	case "json":
        		return MediaType.APPLICATION_JSON;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return MediaType.IMAGE_JPEG; // or MediaType.IMAGE_PNG for PNG files
            // Add more cases for other file types if needed
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    

	@PostMapping("/balu")
	public void adds()
	{
		System.out.println("hello adds");
	}
//	@PostMapping("/gmail")
//	public void fun1(@RequestBody Emailentity entity)
//	{
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setTo(entity.getTo());
//		message.setSubject(entity.getSubject());
//		message.setText(entity.getBody());
//		mailsend.send(message);
//		System.out.println("message");
//	}
}
