package com.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.blog.services.fileservice;

public class fileservicimp  implements fileservice{

	@Override
	public String uplpoadimage(String path, MultipartFile file) throws IOException {
		  String originalFilename = file.getOriginalFilename();

	        // Generate a random name
	        String randomName = UUID.randomUUID().toString();

	        // Extract file extension
	        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

	        // Concatenate random name and file extension
	        String generatedFileName = randomName + fileExtension;

	        // Create the full file path
	        String filePath = path + File.separator + generatedFileName;

	        // Create the directory if it doesn't 11exist
	        File directory = new File(path);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Copy the file content to the new location
	        Files.copy(file.getInputStream(), Paths.get(filePath));

	        return generatedFileName;
	}

	@Override
	public InputStream getresource(String path, String filename) throws FileNotFoundException {
		String fullpath= path+File.separator+filename;
		   
     	 InputStream is = new FileInputStream(fullpath);



return is;
	}

}
