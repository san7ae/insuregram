//package com.wundw.insuregram.dump;
//
//import java.util.Set;
//
///**
// * Interface representing a post in the social media application.
// * A post is created by a user and contains content, comments, and likes.
// * This interface defines the methods to manage the properties of a post.
// */
//public interface IPost {
//
//    /**
//     * Gets the unique identifier for the post.
//     *
//     * @return the unique identifier of the post
//     */
//    Long getId();
//
//    /**
//     * Gets the author (user) of the post.
//     *
//     * @return the user who created the post
//     */
//    IUser getAuthor();
//
//    /**
//     * Gets the content of the post.
//     *
//     * @return the text content of the post
//     */
//    String getContent();
//
//    /**
//     * Adds a comment to the post.
//     * Comments are added by users to the post.
//     *
//     * @param comment the comment to add to the post
//     */
//    void addComment(Comment comment);
//
//    /**
//     * Removes a comment from the post.
//     *
//     * @param comment the comment to remove from the post
//     */
//    void removeComment(Comment comment);
//
//    /**
//     * Gets the set of users who liked the post.
//     *
//     * @return a set of users who liked the post
//     */
//    Set<IUser> getLikes();
//
//    /**
//     * Adds a user to the list of likes for the post.
//     *
//     * @param user the user who liked the post
//     */
//    void addLike(IUser user);
//
//    /**
//     * Removes a user from the list of likes for the post.
//     *
//     * @param user the user who unliked the post
//     */
//    void removeLike(IUser user);
//}
