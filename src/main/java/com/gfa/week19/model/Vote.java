package com.gfa.week19.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter(AccessLevel.NONE) private boolean up;
    @Setter(AccessLevel.NONE) private boolean down;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    public Vote(boolean up, Post post, User user) {
        if (up) {
            this.up = true;
        } else {
            down = true;
        }
        this.post = post;
        this.user = user;
    }

    public boolean voteAgain(boolean up) {
        if (up) {
            if (!this.up && !down) {
                this.up = true;
                return true;
            } else if (!this.up) {
                this.down = false;
                return true;
            }
        }
        else {
            if (!this.up && !down) {
                down = true;
                return true;
            } else if (this.up) {
                this.up = false;
                return true;
            }
        }
    return false;
    }
}