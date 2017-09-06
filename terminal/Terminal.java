package app.terminal;

import app.dto.add.AddFollowerDto;
import app.dto.add.AddPictureDto;
import app.dto.add.AddUserDto;
import app.dto.add.wrappers.CommentsDtoWrapper;
import app.dto.add.wrappers.PostsDtoWrapper;
import app.dto.view.json.PopularUserView;
import app.dto.view.json.UncomentedPostView;
import app.dto.wrappers.UsersXmlDtoWrapper;
import app.dto.xml.UserWithPostCommentsView;
import app.services.CommentService;
import app.services.PictureService;
import app.services.PostService;
import app.services.UserService;
import app.utils.JsonSerializer;
import app.utils.XmlSerializer;
import app.utils.constants.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {
    //services
    private CommentService commentService;
    private PictureService pictureService;
    private PostService postService;
    private UserService userService;

    //helpers
    private JsonSerializer jsonSerializer;
    private XmlSerializer xmlSerializer;

    public Terminal(
            CommentService commentService,
            PictureService pictureService,
            PostService postService,
            UserService userService,
            JsonSerializer jsonSerializer,
            XmlSerializer xmlSerializer) {
        this.commentService = commentService;
        this.pictureService = pictureService;
        this.postService = postService;
        this.userService = userService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    @Override
    public void run(String... strings) throws Exception {

        importPictures();
        importUsers();
        importFollowers();
        importPosts();
        importComments();

        exportUncomentedPosts();
        exportPopularUsers();
        exportUsersWithMostCommentedPost();
    }

    private void exportUsersWithMostCommentedPost() {

        List<UserWithPostCommentsView> users = this.userService.findAllUsersWithMostCommentedPosts();

        UsersXmlDtoWrapper usersWrapper = new UsersXmlDtoWrapper();
        usersWrapper.setUsers(users);

        this.xmlSerializer.serialize(usersWrapper,
                Constants.OUTPUT_XML_FILES_PATH + "comments-on-posts.xml");
    }

    private void exportPopularUsers() {
        List<PopularUserView> popularUsers = this.userService.findAllPopularUsers();

        this.jsonSerializer.serialize(popularUsers,
                Constants.OUTPUT_JSON_FILES_PATH + "popular-users.json");

    }

    private void exportUncomentedPosts() {
        List<UncomentedPostView> posts = this.postService.findAllUncomentedPosts();

        this.jsonSerializer.serialize(posts,
                Constants.OUTPUT_JSON_FILES_PATH + "uncomented-posts.json");

    }

    private void importComments() {
        CommentsDtoWrapper commentsDtoWrapper = this.xmlSerializer.deserialize(CommentsDtoWrapper.class,
                Constants.INPUT_XML_FILES_PATH + "comments.xml");

        this.commentService.save(commentsDtoWrapper.getComments());

    }

    private void importPosts() {
        PostsDtoWrapper postsDtoWrapper = this.xmlSerializer.deserialize(PostsDtoWrapper.class,
                Constants.INPUT_XML_FILES_PATH + "posts.xml");

        this.postService.save(postsDtoWrapper.getPosts());
    }

    private void importFollowers() {
        AddFollowerDto[] followerDtos = this.jsonSerializer.deserialize(AddFollowerDto[].class,
                Constants.INPUT_JSON_FILES_PATH + "users_followers.json");

        this.userService.addFollowers(Arrays.asList(followerDtos));

    }

    private void importUsers() {
        AddUserDto[] users = this.jsonSerializer.deserialize(AddUserDto[].class,
                Constants.INPUT_JSON_FILES_PATH + "users.json");

        this.userService.save(Arrays.asList(users));
    }

    private void importPictures() {
        AddPictureDto[] pics = this.jsonSerializer.deserialize(AddPictureDto[].class,
                Constants.INPUT_JSON_FILES_PATH + "pictures.json");

        this.pictureService.save(Arrays.asList(pics));
    }


}
