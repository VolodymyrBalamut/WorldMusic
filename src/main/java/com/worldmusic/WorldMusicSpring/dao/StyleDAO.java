package com.worldmusic.WorldMusicSpring.dao;
import com.worldmusic.WorldMusicSpring.dao.IDAO;
import com.worldmusic.WorldMusicSpring.model.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


@Repository
public class StyleDAO implements IDAO<Style> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    StyleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Style> findAll() {
        return this.jdbcTemplate.query(
                "select id, name, description from \"Style\"",
                StyleDAO::mapRow);
    }

    @Override
    public List<Style> findById(int id) {
        return this.jdbcTemplate.query(
                "select id, name, description from \"Style\" where id = ?",
                new Object[]{id},
                StyleDAO::mapRow);
    }

    @Override
    public List<Style> findByName(String name) {
        return this.jdbcTemplate.query(
                "select id, name, description from \"Style\" where name = ?",
                new Object[]{name},
                StyleDAO::mapRow);
    }

    @Override
    public Style insert(Style obj){
        return null;
    }

    @Override
    public boolean update(Style obj) {
        return false;
    }

    @Override
    public boolean delete(Style obj) {
        return false;
    }

    private static Style mapRow(ResultSet rs, int rowNum) throws SQLException {
        Style style = Style.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build();
        return style;
    }
}
