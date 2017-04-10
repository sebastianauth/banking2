package com.emma.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportController {
	
	private static final Logger log = LoggerFactory.getLogger(ImportController.class);

    @RequestMapping(value = "api/import/run", method = RequestMethod.POST)
    public @ResponseBody String runImport() {
    	log.info("Controller: Invoke Import Batch");
    	
        return "";
    }
}