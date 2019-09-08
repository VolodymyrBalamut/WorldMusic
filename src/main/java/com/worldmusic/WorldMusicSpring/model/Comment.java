package com.worldmusic.WorldMusicSpring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updatedDate;

    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference
    private Clip clip;

    @PrePersist
    protected void onCreate(){
        createdDate = new Date();
        updatedDate = createdDate;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedDate = new Date();
    }

}
