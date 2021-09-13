package com.vr.hotelService.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vr.hotelService.Entity.AttachmentsEntity;
import com.vr.hotelService.Entity.HotelEntity;
import com.vr.hotelService.Exception.AttachmentNotFoundException;
import com.vr.hotelService.Repository.AttachmentsRepository;

@Service
public class AttachmentsService {
	@Autowired
	private AttachmentsRepository attachmentsRepo;
	
	@Autowired
	private HotelService hotelService;
	
	private final String uploadDir ="./uploads/";
	
	public List<AttachmentsEntity> findAllAttachments(){
		return attachmentsRepo.findAll();
	}
	
	public AttachmentsEntity findAttachment(Long attachmentId) {
		return attachmentsRepo.findById(attachmentId).orElseThrow(()->
		new AttachmentNotFoundException("Attachment "+attachmentId+" doesn't exist") );
	}
	
	public String deleteAttachment(Long attachmentId) {
		AttachmentsEntity attachment = findAttachment(attachmentId);
		if(Objects.nonNull(attachment)) {
			attachmentsRepo.deleteById(attachmentId);
			return "attachment "+attachmentId+ "has been deleted";
		}
		return null;		
	}
	
	public AttachmentsEntity createAttachment(MultipartFile file, Long hotelId) throws IOException {
		AttachmentsEntity attachment = new AttachmentsEntity();
		HotelEntity hotel = hotelService.findHotel(hotelId);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(uploadDir+fileName);
		Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
		attachment.setName(fileName);
		attachment.setHotel(hotel);
		return attachmentsRepo.save(attachment);
	}
	
	public void downloadAttachment(HttpServletRequest req, HttpServletResponse resp, String fileName)
	throws IOException{
		File file = new File(uploadDir + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			resp.setContentType(mimeType);
			resp.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			resp.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, resp.getOutputStream());
		}
	}
}
