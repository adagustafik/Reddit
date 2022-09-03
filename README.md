# The Reddit

## Main functionality
A simple reddit clone application, where users can create posts and upvote / downvote existing posts.

1. Registration + Login/Logout
2. Show TOP 10 posts per page ordered by popularity
3. User is able vote posts up & down
4. User can create new Post & list only his posts


## Run app
IntelliJ IDEA IDE - RUN Reddit/src/main/java/com.gfa.week19/Week19Application.java
serving on localhost:8080/

## Tech Stack
**Java 11**
**Gradle + Gradle Daemon**
automation build tools

**Spring framework + Spring Boot**
spring module for RAD (Rapid App Development)

**MySQL**
**Hibernate + Spring Data JPA**
persistance API

**Lombok**
library of decorators for automated build tools

**Thymeleaf**
template engine


## Architecture
### DB Entities
**User**\
@OneToMany -> Post\
@OneToMany -> Vote

**Post**\
@OneToMany -> Vote\
@ManyToOne -> User

**Vote**\
@ManyToOne -> User\
@ManyToOne -> Post\
boolean up\
boolean down

### Basic data flows
* LOGIN/REGISTER: GET templates/login -> POST UserController getRegisterForm -> UserService loginUser - DB call 
-> if User not found -> save User + setup autContext -> redirect root

* ROOT: PostController listPosts ->  model add User + page -> PostService findAll -> sending PageRequest to repo -> generate templates/index
    - SHOW MY POSTS: PostController getPostsByUser -> boolean myPosts set to true -> redirect root -> PostService getLoggedInUserPosts
      -> sending User & PageRequest to repo -> generate template/index
    - SHOW ALL POSTS: boolean myPost set to false -> redirect root

* VOTING FUNCTIONALITY: PostController votePost @PathVariable id & boolean up ->  PostService votePost -> get Post, get User from repos
-> get Vote by Post & User from repo
    - save to DB new Vote(up, post, user) -> up/down set to true
    - OR voteAgain(up) & save Vote changes to DB -> up/down reset to false OR set to true
    - update Post voteCount & save Post changes to DB (voteCount needed for Post sorting)
    - redirect root


## Lessons learned  
1. AutContext @SessionScope used to store logged in User
2. Pagination & Sorting -> providing Pageable - PageRequest.of(offset, limit) & returning Page
3. Writing native @Query with dynamic @Param for JpaRepository
4. Atomic @Modifying update @Query 
5. Removing data with relationship to a persisting Entity (on delete Foreign Key reset to NULL)
6. @Setter(AccessLevel.NONE) to turn off Lombok auto-generators
7. Thymeleaf - working with Page & setting up Pagination (getTotalPages, getContent)
8. Thymeleaf - #temporals for displaying LocalDateTime format
