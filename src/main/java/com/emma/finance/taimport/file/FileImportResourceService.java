package com.emma.finance.taimport.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FileImportResourceService implements ImportResourceService {
	
//	private final Path rootLocation;
	
	@Autowired
    public FileImportResourceService() {
//        this.rootLocation = Paths.get(null);
    }

	@Override
	public ClassPathResource getImportResource() {
		// TODO Auto-generated method stub
		return null;
	}

}
