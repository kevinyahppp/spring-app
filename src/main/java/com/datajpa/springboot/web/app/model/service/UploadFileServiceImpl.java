package com.datajpa.springboot.web.app.model.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		// TODO Auto-generated method stub
		Path pathPic = getPath(filename);
		log.info("pathPic: " + pathPic);
		Resource resource = null;

		resource = new UrlResource(pathPic.toUri());
		if (!resource.exists() || !resource.isReadable())
			throw new RuntimeException("Error: pic not loaded: " + pathPic.toString());

		return resource;
	}

	@Override
	public String copy(MultipartFile pic) throws IOException {
		// TODO Auto-generated method stub
		// Path path = Paths.get("src//main//resources//static//uploads");
		// String rootPath = path.toFile().getAbsolutePath();
		// String rootPath = "C://temp//uploads";
		String uniqueFileName = UUID.randomUUID().toString() + "_" + pic.getOriginalFilename();
		Path rootPath = getPath(uniqueFileName);
		log.info("rootPath: " + rootPath);

		// byte[] bytes = pic.getBytes();
		// Path completeRoute = Paths.get(rootPath + "//" + pic.getOriginalFilename());
		// Files.write(completeRoute, bytes);
		Files.copy(pic.getInputStream(), rootPath);

		return uniqueFileName;
	}

	@Override
	public boolean delete(String filename) {
		// TODO Auto-generated method stub
		Path rooPath = getPath(filename);
		File file = rooPath.toFile();
		if (file.exists() && file.canRead()) {
			if (file.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		// TODO Auto-generated method stub
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}
}
