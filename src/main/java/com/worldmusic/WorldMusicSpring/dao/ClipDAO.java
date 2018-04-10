package com.worldmusic.WorldMusicSpring.dao;
import com.worldmusic.WorldMusicSpring.dao.IDAO;
import com.worldmusic.WorldMusicSpring.model.Clip;
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
public class ClipDAO implements IDAO<Clip> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    ClipDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Clip> findAll() {
        return this.jdbcTemplate.query(
                "select id, name, url from \"Clip\"",
                ClipDAO::mapRow);
    }

    @Override
    public List<Clip> findById(int id) {
        return this.jdbcTemplate.query(
                "select id, name, url from \"Clip\" where id = ?",
                new Object[]{id},
                ClipDAO::mapRow);
    }

    @Override
    public List<Clip> findByName(String name) {
        return this.jdbcTemplate.query(
                "select id, name, url from \"Clip\" where name = ?",
                new Object[]{name},
                ClipDAO::mapRow);
    }

    @Override
    public Clip insert(Clip obj){
        final String sql = "insert into \"Clip\" (name,url,performer_id,style_id) values (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row= this.jdbcTemplate.update(
                new PreparedStatementCreator(){
                    public PreparedStatement createPreparedStatement(Connection connection)
                            throws SQLException {
                        PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1,obj.getName());
                        ps.setString(2, obj.getUrl());
                        //ps.setInt(3, obj.getPerformer_id());
                        //ps.setInt(4, (Integer)((Style)(obj.getStyle()).getId()));
                        return ps;
                    }},
                keyHolder);

        /*Object newPersonId = keyHolder.getKeys().get("id");
        user.setId((Integer)newPersonId);*/
        return obj;
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
