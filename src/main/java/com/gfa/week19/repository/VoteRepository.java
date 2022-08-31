package com.gfa.week19.repository;

import com.gfa.week19.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "select vote.* from vote join post on vote.post_id = post.id join user on " +
            "vote.user_id = user.id where post.id = :postid and user.id = :userid", nativeQuery = true)
    Vote findByPostAndUser(@Param("postid")Long postid, @Param("userid")Long userid);
}
