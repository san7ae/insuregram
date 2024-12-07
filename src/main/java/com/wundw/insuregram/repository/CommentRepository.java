package com.wundw.insuregram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wundw.insuregram.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
