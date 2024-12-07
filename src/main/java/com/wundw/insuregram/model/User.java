package com.wundw.insuregram.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    //@OneToMany Indicates a one-to-many relationship (one Author can have many Post objects).
    /*
     * @OneToMany: Indicates a one-to-many relationship (one Author can have many Post objects).
     * mappedBy = "author": Refers to the field in the Post entity that maps back to Author.
     * cascade = CascadeType.ALL: Any changes to the Author (persist, update, delete) cascade to the related Post objects.
     * cascade means that specific operations on a parent entity (e.g., Author) are automatically applied to its related child entities (e.g., Post).
     * orphanRemoval = true: If a Post is removed from the list, it's deleted from the database.
     * Changed IPost to Post: Indicates a class/interface change to make the relationship functional.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts; // Changed IPost to Post for implementation

    /**
     * ManyToMany: Indicates a many-to-many relationship (a User can have many Followers, and a Follower can follow many Users).
     * @JoinTable: Specifies the name and structure of the join table.
     * name = "user_followers": The join table's name in the database.
     * joinColumns: Maps the user_id column to the User entity.
     * inverseJoinColumns: Maps the follower_id column to the Follower entity
     */
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>(); // Initialized to prevent null issues

    /*
     * In this case:
     * The following set refers to the followers field in the other entity, which already has the @JoinTable definition.
     * There's no need to duplicate it.
     */
    @ManyToMany(mappedBy = "followers")
    private Set<User> following = new HashSet<>(); // Initialized to prevent null issues

    // Getters and Setters
    
    public Long getId() {
        return this.id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getUsername() {
        return this.username;
    }

    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getEmail() {
        return this.email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * Immutability means that the state of an object cannot be changed after it is created. In this context:
     * By returning a copy of the following set instead of the original, the caller cannot directly modify the original set stored in the object. 
     * This prevents accidental or intentional changes to the internal state of the object.
     */
    
    public Set<User> getFollowing() {
        return new HashSet<>(this.following); // Returns a copy for immutability
    }

    
    public void setFollowing(Set<User> following) {
        this.following = new HashSet<>();
        for (User user : following) {
            this.following.add((User) user);
        }
    }

    
    public boolean follow(User user) {
        User target = (User) user;
        if (!this.following.contains(target)) {
            this.following.add(target);
            target.followers.add(this); // Add to the target user's followers
            return true;
        }
        return false;
    }

    
    public boolean unfollow(User user) {
        User target = (User) user;
        if (this.following.contains(target)) {
            this.following.remove(target);
            target.followers.remove(this); // Remove from the target user's followers
            return true;
        }
        return false;
    }

    /*
     * Set is used when you don't care about the order and want to avoid duplicates. For followers and following, we use Set<User> because
     * Each User should only follow another User once (no duplicates).
     * The order of followers or following is typically not important.
     */
    
    public Set<User> getFollowers() {
        return new HashSet<>(this.followers); // Returns a copy for immutability
    }

    
    public void setFollowers(Set<User> followers) {
        this.followers = new HashSet<>();
        for (User user : followers) {
            this.followers.add((User) user);
        }
    }

    
    /*
     * List is used when the order of elements matters or when duplicates are allowed.
     * In the posts field, it uses List<Post> because a user may have multiple posts in a specific order (e.g., chronological), and duplicates could be allowed.
     */
    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
