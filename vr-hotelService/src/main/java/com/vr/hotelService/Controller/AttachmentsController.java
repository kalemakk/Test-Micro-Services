package com.vr.hotelService.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vr.hotelService.Entity.AttachmentsEntity;
import com.vr.hotelService.Service.AttachmentsService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/attachments")
public class AttachmentsController {
	
	@Autowired
	private AttachmentsService attachmentsService;
	
	@GetMapping
	public List<AttachmentsEntity> findAllAttachments() {
		return attachmentsService.findAllAttachments();
	}
	
	@GetMapping("/{id}")
	public AttachmentsEntity findAttachment(@PathVariable("id") Long attachmentId) {
		return attachmentsService.findAttachment(attachmentId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteAttachment(Long attachmentId) {
		return attachmentsService.deleteAttachment(attachmentId);
	}
	
	@PostMapping("/{hotelId}")
	public AttachmentsEntity createAttachment(@RequestParam("file") MultipartFile file, 
			@PathVariable("hotelId") Long hotelId ) throws IOException {
		return attachmentsService.createAttachment(file, hotelId);
	}
	
	@GetMapping("/download")
    public void download(@RequestParam("file") String fileName,
                         HttpServletResponse resp, HttpServletRequest req) throws Exception{
		         attachmentsService.downloadAttachment(req, resp, fileName);
    }	
}
