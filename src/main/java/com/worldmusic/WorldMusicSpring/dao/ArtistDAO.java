package com.worldmusic.WorldMusicSpring.dao;
import com.worldmusic.WorldMusicSpring.dao.IDAO;
import com.worldmusic.WorldMusicSpring.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Repository
public class ArtistDAO implements IDAO<Artist>{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ArtistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Artist> findAll() {
        return this.jdbcTemplate.query(
                "select id, name, biography, country_code from \"artists\"",
                ArtistDAO::mapRow);
    }

    @Override
    public List<Artist> findById(int id) {
        return this.jdbcTemplate.query(
                "select id, name, biography, country_code from \"artists\" where id = ?",
                new Object[]{id},
                ArtistDAO::mapRow);
    }

    @Override
    public List<Artist> findByName(String name) {
        return this.jdbcTemplate.query(
                "select id, name, biography, country_code from \"artists\" where name = ?",
                new Object[]{name},
                ArtistDAO::mapRow);
    }

    public List<Artist> findByCountryCode(String country_code) {
        return this.jdbcTemplate.query(
                "select id, name, biography, country_code from \"artists\" where country_code = ?",
                new Object[]{country_code},
                ArtistDAO::mapRow);
    }

    @Override
    public Artist insert(Artist obj){
        return null;
    }

    @Override
    public boolean update(Artist obj) {
        return false;
    }

    @Override
    public boolean delete(Artist obj) {
        return false;
    }

    private static Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Artist artist = Artist.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .biography(rs.getString("biography"))
                //.country_code(rs.getString("country_code"))
                .build();
        return artist;
    }
}
