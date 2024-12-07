package com.wundw.insuregram.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wundw.insuregram.model.Post;
import com.wundw.insuregram.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);

	List<Post> findByAuthorIn(Set<User> following);
}