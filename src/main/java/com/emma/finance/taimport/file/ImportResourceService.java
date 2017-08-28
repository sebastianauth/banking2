package com.emma.finance.taimport.file;

import org.springframework.core.io.ClassPathResource;

/**
 * @author Sebastian
 *
 */
public interface ImportResourceService {
	
	ClassPathResource getImportResource();
	
	void archiveImportResource();
	
//	List<Resource> readImportFolder();

}
