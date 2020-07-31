/**
 * 
 */
package com.nks.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author snirm
 *
 */
@RestController
public class TestingRestController implements ServletContextAware {

	private ServletContext servletContext;
	private final static Logger log = LoggerFactory.getLogger(TestingRestController.class);

	@GetMapping("/hello")
	public String hello() {
		return "Hello world";
	}

	@PostMapping("/upload")
	public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) {

		try {
			log.info("Files Processing ....");
			log.info("******************************");
			for (MultipartFile file : files) {
				log.info("" + file.getOriginalFilename());
				log.info("" + file.getSize());
				log.info("" + file.getContentType());
				save(file);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler({FileSizeLimitExceededException.class, FileNotFoundException.class})
	private String save(MultipartFile file) {
		String filename = null;
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
			filename = dateFormat.format(new Date()) + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(this.servletContext.getRealPath("/images/" + filename));

			File dir = new File(this.servletContext.getRealPath("/images/"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			Files.write(path, bytes);
			log.info(file.getOriginalFilename()+ " File stored in folder ....");
			log.info("******************************");
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return filename;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
	
	
}
