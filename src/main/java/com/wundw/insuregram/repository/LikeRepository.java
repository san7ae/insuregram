package com.wundw.insuregram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wundw.insuregram.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	
}