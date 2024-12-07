package com.wundw.insuregram.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Represents a social media post created by a user.
 * A post contains content, comments, and likes, and is associated with an author (user).
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
        name = "post_likes",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likes = new HashSet<>();

    // Constructors
    public Post(User author, String content) {
        this.author = author;
        this.content = content;
    }

    // Getter and Setter for ID (ID should not be directly set externally)
    public Long getId() {
        return id;
    }

    // Author (user who created the post)
    public User getAuthor() {
        return author;
    }

    // Content of the post
    public String getContent() {
        return content;
    }

    /**
     * Validates and sets the content for the post.
     * The content should not be empty or null.
     * 
     * @param content the content to set
     * @throws IllegalArgumentException if the content is invalid
     */
    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        this.content = content;
    }

    // Comments related to this post
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the post.
     * The comment is automatically associated with this post.
     * 
     * @param comment the comment to add
     */
    public void addComment(Comment comment) {
        if (comment != null && !comments.contains(comment)) {
            comments.add(comment);
            comment.setPost(this); // Associate the comment with this post
        }
    }

    /**
     * Removes a comment from the post.
     * 
     * @param comment the comment to remove
     */
    public void removeComment(Comment comment) {
        if (comment != null && comments.contains(comment)) {
            comments.remove(comment);
            comment.setPost(null); // Disassociate the comment from the post
        }
    }

    // Likes associated with the post
    public Set<User> getLikes() {
        return new HashSet<>(likes); // Return a copy to avoid external modification
    }

    /**
     * Adds a user to the set of likes for the post.
     * 
     * @param user the user who liked the post
     */
    public void addLike(User user) {
        if (user != null && !likes.contains(user)) {
            likes.add((User) user); // Cast IUser to User
        }
    }

    /**
     * Removes a user from the set of likes for the post.
     * 
     * @param user the user who unliked the post
     */
    public void removeLike(User user) {
        if (user != null && likes.contains(user)) {
            likes.remove(user);
        }
    }
}
