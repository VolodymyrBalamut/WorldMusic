package com.worldmusic.WorldMusicSpring.services;

import com.worldmusic.WorldMusicSpring.model.Clip;
import com.worldmusic.WorldMusicSpring.model.Comment;
import com.worldmusic.WorldMusicSpring.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public List<Comment> getAllComments(){
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll()
                .forEach(comments::add);
        return comments;
    }

    public Comment getComment(int id){
        return commentRepository.findById(id).get();
    }

    public List<Comment> getCommentsByClip(int id){
        List<Comment> comments = new ArrayList<>();
        commentRepository.findByClip_id(id)
                .forEach(comments::add);
        return comments;
    }

    public List<Comment> getCommentsByUser(int id){
        List<Comment> comments = new ArrayList<>();
        commentRepository.findByUser_id(id)
                .forEach(comments::add);
        return comments;
    }

    public Comment addComment(Comment comment){
        commentRepository.save(comment);
        return comment;
    }
    public Comment updateComment(Comment comment){
        commentRepository.save(comment);
        return comment;
    }
    public boolean deleteComment(int id){
        commentRepository.deleteById(id);
        return true;
    }

    public long getCount(){
        return commentRepository.count();
    }
}
