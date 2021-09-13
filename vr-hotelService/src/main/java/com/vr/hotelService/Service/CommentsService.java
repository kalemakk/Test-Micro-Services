package com.vr.hotelService.Service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vr.hotelService.Repository.CommentsRepository;
import com.vr.hotelService.Entity.CommentsEntity;
import com.vr.hotelService.Entity.HotelEntity;
import com.vr.hotelService.Exception.CommentNotFoundException;

@Service
public class CommentsService {
	@Autowired
	private CommentsRepository commentsRepo;
	
	@Autowired
	private HotelService hotelService;
	
	public List<CommentsEntity> findAllComments(){
		return commentsRepo.findAll();
	}
	
	public CommentsEntity findComment(Long commentId) {
		return commentsRepo.findById(commentId).orElseThrow(()-> 
		new CommentNotFoundException("Comment with id "+commentId+" doesn't exist"));
	}
	
	public CommentsEntity createComment(Long hotelId, CommentsEntity comment) {
		HotelEntity hotel = hotelService.findHotel(hotelId);
		comment.setHotel(hotel);
		return commentsRepo.save(comment);		
	}
	
	public String deleteComment(Long commentId) {
		CommentsEntity comment = findComment(commentId);
		if(Objects.nonNull(comment)) {
			commentsRepo.deleteById(commentId);
		}
		return "Comment "+commentId+" deleted. ";		
	}
	
	public CommentsEntity updateComment(Long commentId, CommentsEntity comment) {
		CommentsEntity oldComment = findComment(commentId);
		comment.setCommentId(commentId);
		comment.setHotel(oldComment.getHotel());
		return commentsRepo.save(comment);
	}
}
