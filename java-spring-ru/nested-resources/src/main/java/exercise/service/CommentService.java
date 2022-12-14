package exercise.service;

import exercise.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import liquibase.pro.packaged.C;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Iterable<Comment> getAllCommentsOfPost(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public Comment getCommentOfPost(Long postId, Long commentId) {
        return commentRepository.findFirstByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Comment with id: %s for post with id: %s not found!", commentId, postId)));
    }

    public void createCommentForPost(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id: %s not found!", postId)));

        comment.setPost(post);

        post.getComments().add(commentRepository.save(comment));
        postRepository.save(post);
    }

    public void updateComment(Long postId, Long commentId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id: %s not found!", postId)));
        Comment oldComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment with id: %s not found!", commentId)));

        oldComment.setContent(comment.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id: %s not found!", postId)));
        Comment comment = commentRepository.findFirstByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment with id: %s not found!", commentId)));


        post.getComments().remove(comment);

        postRepository.save(post);
        commentRepository.delete(comment);
    }

}
