package com.example.plan.xmlEntityControlller;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import  com.example.plan.plan_info.repository.*;

@RestController
@CrossOrigin("*")
public class xmlController {
	
	private final xmlRepo repo;
	@Autowired
	public xmlController(xmlRepo repo) {
		this.repo = repo;
	}
	@GetMapping("/getxml")
	public List<xmlEntity> getXmlData() {
		return repo.findAll();
	}
	@PostMapping("/process-xml")
	public ResponseEntity<String> processXmlData(@RequestBody String xmlData)
	{
		
		try {
			System.out.println("erro-1");
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
			Document document = dbuilder.parse(new InputSource(new StringReader(xmlData)));
			
			
		
			Element root=(Element) document.getElementsByTagName("plan_info").item(0);
//			NodeList personList= root.getElementsByTagName("person");
			xmlEntity xmlentity = new xmlEntity();
			
				xmlentity.setEvId(getElementValue(root,"ev_id"));
			 	xmlentity.setPlanName(getElementValue(root, "plan_name"));
	            xmlentity.setIrsrlCode(getElementValue(root, "irsrl_code"));
	            xmlentity.setProvCompany(getElementValue(root, "prov_company"));
	            xmlentity.setProdId(getElementValue(root, "prod_id"));
	            xmlentity.setStatusCode(getElementValue(root, "status_code"));
	            xmlentity.setDpDateTime(getElementValue(root, "dpdate_time"));
	            xmlentity.setRolloverInd(getElementValue(root, "rollover_ind"));
	            xmlentity.setGaId(getElementValue(root, "ga_id"));
	            xmlentity.setClientName(getElementValue(root, "client_name"));
	            xmlentity.setFirstLineMailing(getElementValue(root, "first_line_mailing"));
	            xmlentity.setScndLineMailing(getElementValue(root, "scnd_line_mailing"));
	            xmlentity.setCity(getElementValue(root, "city"));
	            xmlentity.setStateCode(getElementValue(root, "state_code"));
	            xmlentity.setZipCode(getElementValue(root, "zip_code"));
	            xmlentity.setCountry(getElementValue(root, "country"));
	            xmlentity.setInternalComplianceInd(getElementValue(root, "internal_compliance_ind"));
	            xmlentity.setPlanAnnivDate(getElementValue(root, "plan_anniv_date"));
	            xmlentity.setEstNumberOfParticipants(getElementValue(root, "est_number_of_participants"));
	            xmlentity.setEstPlanAssets(getElementValue(root, "est_plan_assets"));
	            xmlentity.setEstImplementationDate(LocalDate.parse(getElementValue(root, "est_implementation_date")));
	            xmlentity.setSubsetCashCode(getElementValue(root, "subset_cash_code"));
	            xmlentity.setClientEntityType(getElementValue(root, "client_entity_type"));
	            xmlentity.setTpaFirmId(getElementValue(root, "tpa_firm_id"));
	            System.out.println("erro2");
	            repo.save(xmlentity);

			return new ResponseEntity<String>("data success",HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<String>("failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		}
		private String getElementValue(Element parseElement,String childTagName) {
			NodeList childnodes = parseElement.getElementsByTagName(childTagName);
			if(childnodes.getLength()>0) {
				return childnodes.item(0).getTextContent();
			}
			return null;
		}

		
	}

