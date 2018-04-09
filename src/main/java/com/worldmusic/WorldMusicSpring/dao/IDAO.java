package com.worldmusic.WorldMusicSpring.dao;

import java.util.List;

public interface IDAO<T> {
    List<T> findAll();
    List<T> findById(int id);
    List<T> findByName(String name);
    T insert(T obj);
    boolean update(T obj);
    boolean delete(T obj);
}
