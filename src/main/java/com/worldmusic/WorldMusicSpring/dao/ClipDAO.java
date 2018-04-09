package com.worldmusic.WorldMusicSpring.dao;
import com.worldmusic.WorldMusicSpring.dao.IDAO;
import com.worldmusic.WorldMusicSpring.model.Clip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


@Repository
public class ClipDAO implements IDAO<Clip> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ClipDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Clip> findAll() {
        return this.jdbcTemplate.query(
                "select id, name, url from \"clips\"",
                ClipDAO::mapRow);
    }

    @Override
    public List<Clip> findById(int id) {
        return this.jdbcTemplate.query(
                "select id, name, url from \"clips\" where id = ?",
                new Object[]{id},
                ClipDAO::mapRow);
    }

    @Override
    public List<Clip> findByName(String name) {
        return this.jdbcTemplate.query(
                "select id, name, url from \"clips\" where name = ?",
                new Object[]{name},
                ClipDAO::mapRow);
    }

    @Override
    public boolean insert(Clip obj){
        return false;
    }

    @Override
    public boolean update(Clip obj) {
        return false;
    }

    @Override
    public boolean delete(Clip obj) {
        return false;
    }

    private static Clip mapRow(ResultSet rs, int rowNum) throws SQLException {
        Clip clip = Clip.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .url(rs.getString("url"))
                .build();
        return clip;
    }
}
