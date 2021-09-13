package com.vr.hotelService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vr.hotelService.Service.CommentsService;
import com.vr.hotelService.Entity.CommentsEntity;

@RestController
@CrossOrigin
@RequestMapping("api/v1/hotel-comments")
public class CommentsController {
	@Autowired
	private CommentsService commentsService;
	
	@GetMapping
	public List<CommentsEntity> findAllComments(){
		return commentsService.findAllComments();
	}
	
	@GetMapping("/{id}")
	public CommentsEntity findComment(@PathVariable("id") Long commentId) {
	 return commentsService.findComment(commentId);	
	}	
	
	@PostMapping("/{hotelId}")
	public CommentsEntity createComment(@PathVariable("hotelId") Long hotelId,
			@RequestBody CommentsEntity comment) {
		return commentsService.createComment(hotelId, comment);
	}
	
	@DeleteMapping("/{id}")
	public String deleteComment(@PathVariable("id") Long commentId) {
		return commentsService.deleteComment(commentId);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommentsEntity> updateComment(@PathVariable("id") Long commentId,
			@RequestBody CommentsEntity comment){
		return ResponseEntity.ok(this.commentsService.updateComment(commentId, comment));
	}
}
