package app.services;

import app.dto.add.AddFollowerDto;
import app.dto.add.AddUserDto;
import app.dto.view.json.PopularUserView;
import app.dto.xml.UserWithPostCommentsView;
import app.models.Comment;
import app.models.Picture;
import app.models.Post;
import app.models.User;
import app.repositories.PictureRepository;
import app.repositories.UserRepository;
import app.utils.DtoMappingUtil;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void save(AddUserDto user) {
    }

    @Override
    public void save(Iterable<AddUserDto> users) {

        for (AddUserDto userDto : users) {
            String profilePicUrl = userDto.getProfilePicture();

            Picture pic = this.pictureRepository.findOneByPath(profilePicUrl);
            User user = DtoMappingUtil.convert(userDto, User.class);

            user.setProfilePicture(pic);

            if(ValidationUtil.isValid(user)){
                this.userRepository.saveAndFlush(user);
                System.out.println(String.format("Successfully imported User %s.", user.getUsername()));
            }else{
                System.out.println("Error: Invalid data.");
            }

        }
    }

    @Override
    public void addFollowers(Iterable<AddFollowerDto> followerDtos) {

        for (AddFollowerDto followerDto : followerDtos) {
            String userName = followerDto.getUser();
            String followerName = followerDto.getFollower();

            User user = this.userRepository.findOneByUsername(userName);
            User follower = this.userRepository.findOneByUsername(followerName);

            if(user != null && follower != null){
                user.getFollowers().add(follower);
                follower.getFollowing().add(user);

                this.userRepository.save(user);
                this.userRepository.save(follower);

                System.out.println(String.format("Successfully imported Follower %s to User %s.", follower.getUsername(), user.getUsername()));
            }else{
                System.out.println("Error: Invalid data.");
            }
        }
    }

    @Override
    public List<PopularUserView> findAllPopularUsers() {
        List<User> users = this.userRepository.findAll();

        String debug = "";

        users = users
                .stream()
                .filter(x -> this.isPopular(x))
                .sorted((a,b) -> Integer.compare(b.getId(), a.getId()))
                .collect(Collectors.toList());

        List<PopularUserView> popularUsers = DtoMappingUtil.convert(users, PopularUserView.class);

        for (PopularUserView popularUser : popularUsers) {
            popularUser.setFollowersCount(popularUser.getFollowers().size());
        }

        return popularUsers;
    }

    @Override
    public List<UserWithPostCommentsView> findAllUsersWithMostCommentedPosts() {
        List<User> users = this.userRepository.findAll();
        List<UserWithPostCommentsView> outputUsers = new ArrayList<>();

        for (User user : users) {
            UserWithPostCommentsView userView = new UserWithPostCommentsView();

            Post post = this.findMostCommentedPost(user);

            userView.setUsername(user.getUsername());
            if(post == null){
                userView.setMostComments(0);
            }else{
               userView.setMostComments(post.getComments().size());
            }

            outputUsers.add(userView);
        }

        outputUsers = outputUsers
                .stream()
                .sorted((a,b) -> Integer.compare(b.getMostComments(), a.getMostComments()))
                .collect(Collectors.toList());

        return outputUsers;
    }

    private Post findMostCommentedPost(User user) {
        Post mostCommented = null;
        int currentPostsCount = 0;

        for (Post post : user.getPosts()) {

            if(post.getComments().size() > currentPostsCount){
                mostCommented = post;
                currentPostsCount = post.getComments().size();
            }

        }

        return mostCommented;
    }

    private boolean isPopular(User x) {
        Set<User> followers = x.getFollowers();

        for (Post post : x.getPosts()) {
            for (Comment comment : post.getComments()) {
                if(followers.contains(comment.getUser())){
                    return true;
                }
            }
        }

        return false;
    }

}
