package app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Picture profilePicture;
    private Set<User> followers;
    private Set<User> following;
    private Set<Post> posts;

    public User() {
        followers = new HashSet<>();
        following = new HashSet<>();
        posts = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "picture_id")
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_followers", joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="follower_id"))
    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_following", joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="following_id"))
    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
