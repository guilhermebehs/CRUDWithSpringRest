package br.com.guilhermebehs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
        String os = System.getProperty("os.name");
        if(!os.toLowerCase().contains("windows"))
        	this.uploadDir = "/home/guilhermebehs/Documents/files";
        else
		    this.uploadDir = uploadDir;
	}
	
}