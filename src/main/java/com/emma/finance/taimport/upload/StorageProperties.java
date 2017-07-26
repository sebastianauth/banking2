package com.emma.finance.taimport.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {
	
    /**
     * Folder location for storing files
     */
    private String location = "C:\\Users\\Sebastian\\git\\Banking2\\src\\main\\resources\\import";
	
//	@Value("${import.file.path}")
//	private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
