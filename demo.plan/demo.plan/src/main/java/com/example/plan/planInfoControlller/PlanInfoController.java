package com.example.plan.planInfoControlller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.plan.plan_info.entity.PlanInfo;
import com.example.plan.plan_info.service.PlanInfoService;
import com.example.plan.plan_info.service.PlansService;

@RestController
@CrossOrigin("*")
public class PlanInfoController {

	@Autowired
	private PlanInfoService serv;
	
	@GetMapping("/getplaninfo")
	public List<PlanInfo> getPlansInfo()
	{
		return serv.getPlansInfo();
	}
	@GetMapping("/list")
    public ResponseEntity<List<String>> getFileList() {
        File folder = new File("C:\\TestXML");
        String[] fileList = folder.list();
        return ResponseEntity.ok(Arrays.asList(fileList));
    }
	
	private static final String D_DRIVE_PATH = "D:/your/folder/path/";

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName) throws IOException {
        File file = new File("C:\\TestXML"+ fileName);
        System.out.println("error1");
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            System.out.println("error2");
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            System.out.println("error3");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
        	System.out.println("error4");
            return ResponseEntity.notFound().build();
        }
    }
}
