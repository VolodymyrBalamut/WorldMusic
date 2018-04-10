package com.worldmusic.WorldMusicSpring.dao;
import com.worldmusic.WorldMusicSpring.dao.IDAO;
import com.worldmusic.WorldMusicSpring.model.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        final String sql = "insert into \"Style\" (name,description) values (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row= this.jdbcTemplate.update(
                new PreparedStatementCreator(){
                    public PreparedStatement createPreparedStatement(Connection connection)
                            throws SQLException {
                        PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1,obj.getName());
                        ps.setString(2, obj.getDescription());
                        return ps;
                    }},
                keyHolder);

        /*Object newPersonId = keyHolder.getKeys().get("id");
        user.setId((Integer)newPersonId);*/
        return obj;
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
