package br.com.guilhermebehs.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.guilhermebehs.data.vos.UploadFileResponseVO;
import br.com.guilhermebehs.services.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
    private FileStorageService fileStorageService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/upload")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				  .path("/file/download/")
				  .path(fileName)
				  .toUriString();
		
		String fileType = file.getContentType();
		long fileSize = file.getSize();
		
		return new UploadFileResponseVO(fileName, fileDownloadUri, fileType, fileSize);
	}
	
	@PostMapping("/uploadMany")
	public List<UploadFileResponseVO> uploadFiles(@RequestParam("files") MultipartFile[] files) {
	    return Arrays.asList(files)
	    		 .stream()
	    		 .map(file -> uploadFile(file))
	    		 .collect(Collectors.toList());
	}
	
	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch (Exception e) {
			logger.info("could not determine file type!");
		}
		if(contentType == null)
			contentType = "application/octet-stream";
		
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"")
				.body(resource);
	}    
	
}
