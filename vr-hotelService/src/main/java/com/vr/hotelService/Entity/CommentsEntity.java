package com.vr.hotelService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="hotel_comments")
public class CommentsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commentId;	
	
	@Column(nullable=false) 
	private String description;
	
	@Column(nullable=false)
	private String commentedBy;
	
	@ManyToOne(targetEntity = HotelEntity.class)
	@JoinColumn(name="hotel_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private HotelEntity hotel;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommentedBy() {
		return commentedBy;
	}	

	public HotelEntity getHotel() {
		return hotel;
	}

	public void setHotel(HotelEntity hotel) {
		this.hotel = hotel;
	}

	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}	

	public CommentsEntity(String description, String commentedBy, HotelEntity hotel) {
		this.description = description;
		this.commentedBy = commentedBy;
		this.hotel = hotel;
	}

	public CommentsEntity() {}	
}
