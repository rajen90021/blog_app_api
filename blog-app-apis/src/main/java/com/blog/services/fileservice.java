package com.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface fileservice {
public String uplpoadimage(String path ,MultipartFile file) throws IOException;
	
	
	public InputStream    getresource(String path,String filename) throws FileNotFoundException;
}
