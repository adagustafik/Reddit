package com.gfa.week19.service;

import com.gfa.week19.model.Post;
import com.gfa.week19.model.User;
import com.gfa.week19.model.Vote;
import com.gfa.week19.repository.PostRepository;
import com.gfa.week19.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final AutContext autContext;
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;

    @Override
    public Page<Post> findAll(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size,
                Sort.by("votesCount").descending().and(Sort.by("added")).descending()));
    }

    @Override
    public Page<Post> getLoggedInUserPosts(int page, int size) {
        return postRepository.findAllByUser(autContext.getUser(), PageRequest.of(page, size,
                Sort.by("votesCount").descending().and(Sort.by("added")).descending()));
    }

    @Override
    public void addPost(Post post) {
        post.setUser(autContext.getUser());
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void votePost(Long id, Boolean up) {
        Post votedUp = postRepository.getById(id);
        User user = autContext.getUser();
        Vote vote = voteRepository.findByPostAndUser(id, user.getId());
        if (Objects.isNull(vote)) {
            voteRepository.save(new Vote(up, votedUp, user));
            votePostRepository(id, up);
        } else if (vote.voteAgain(up)) {
            voteRepository.save(vote);
            votePostRepository(id, up);
        }
    }

    public void votePostRepository(Long id, Boolean up) {
        if (up) {
            postRepository.upvotePost(id);
        } else {
            postRepository.downvotePost(id);
        }
    }
}
