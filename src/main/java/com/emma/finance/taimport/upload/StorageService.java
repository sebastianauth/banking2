package com.emma.finance.taimport.upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Sebastian
 *
 */
public interface StorageService {

    /**
     * Stores file in configured location
     * 
     * @param file
     */
    void store(MultipartFile file);

    /**
     * @return
     */
    Stream<Path> loadAll();

    /**
     * @param filename
     * @return
     */
    Path load(String filename);

    /**
     * @param filename
     * @return
     */
    Resource loadAsResource(String filename);

}