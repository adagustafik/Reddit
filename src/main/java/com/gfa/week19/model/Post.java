package com.gfa.week19.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String url;
    private int votesCount = 0;

    @CreationTimestamp
    private LocalDateTime added;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(
        foreignKey = @ForeignKey(foreignKeyDefinition =
                "foreign key (user_id) references `user`(`id`) on delete set NULL"))
    private User user;
}