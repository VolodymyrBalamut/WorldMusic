package com.worldmusic.WorldMusicSpring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.worldmusic.WorldMusicSpring.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
    List<Comment> findByClip_id(int id);
    List<Comment> findByUser_id(int id);
}
