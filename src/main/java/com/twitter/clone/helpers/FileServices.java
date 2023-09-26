package com.twitter.clone.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

public class FileServices {
	
	//user image validation
	public List<String> userImageValidation(MultipartFile file) {
		List<String> error = new ArrayList<String>();		
		
		if(file.isEmpty()) {
			error.add("Insert your Image");
		}else if(!file.getContentType().equalsIgnoreCase("image/png") && !file.getContentType().equalsIgnoreCase("image/jpeg") ) {
			error.add("Invalid File type. Insert jpg, png file type only");			
		}else if(file.getSize() > 5000000 /*2 MB*/) {
			error.add("File size is too large");
		}		
		return error;
	}

	
	
	//uploading user image
	public String uploadUserImage(MultipartFile file, HttpSession session) throws IOException {		
		
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getStorageDescription());
		
		
		SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }
        String randomHexCode = sb.toString();
		String file_name = ""+randomHexCode+file.getOriginalFilename();
		
		byte[] data = file.getBytes();
		
		String path = session.getServletContext().getRealPath("/")+"resources"+File.separator+"image"+File.separator+"userImages"+ File.separator+file_name;
		System.out.println(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
		}catch (Exception e) {
			System.out.println("File upload failed");
			System.out.println(e);
			return null;
		}		
		return file_name;
	}
	
	
	
	
	
	
	
	
	
	
	
	//get the file type
	public String postFileType(MultipartFile file) {
		
		String type = "";
		if(file.getContentType().equalsIgnoreCase("image/jpeg") || file.getContentType().equalsIgnoreCase("image/png")) {
			type = "image";
		}else if(file.getContentType().equalsIgnoreCase("video/mp4") || file.getContentType().equalsIgnoreCase("video/x-msvideo")) {
			type = "video";
		}
		
		return type;
	}
	
	
	
	//post image validation
	public List<String> postImageValidation(MultipartFile image) {		
		List<String> error = new ArrayList<String>();		
		if(image.getSize() > 5000000 /*5 Mb*/ && image.getSize() > 100) {
			error.add("Image size should under 5 Mb");
		}		
		return error;
	}
	
	
	public List<String> postVideoValidation(MultipartFile video) {		
		List<String> error = new ArrayList<String>();		
		if(video.getSize() > 30000000 /*10 Mb*/) {
			error.add("Video size should under 30 Mb");
		}		
		return error;
	}	
	

	
	
	

	//UPLOAD POST FILE
	public String uploadPostFile(MultipartFile file, HttpSession session) throws IOException {
		
		SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : randomBytes) {
            sb.append(String.format("%02x", b));
        }
        String randomHexCode = sb.toString();
//		GENERATING RANDOM TEXT ENDS
		
		
        String file_name = ""+randomHexCode+file.getOriginalFilename();
		
		byte[] data = file.getBytes();
		
		String path = session.getServletContext().getRealPath("/")+"resources"+File.separator+"post_files"+File.separator+file_name;
		System.out.println(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
		}catch (Exception e) {
			System.out.println("Post file upload failed" + e);
			return null;
		}		
		return file_name;
	}
}
