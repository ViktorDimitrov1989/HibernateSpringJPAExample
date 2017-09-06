package app.services;

import app.dto.add.AddPostDto;
import app.dto.view.json.UncomentedPostView;
import app.models.Picture;
import app.models.Post;
import app.models.User;
import app.repositories.PictureRepository;
import app.repositories.PostRepository;
import app.repositories.UserRepository;
import app.utils.DtoMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PictureRepository pictureRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           PictureRepository pictureRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(AddPostDto post) {
    }

    @Override
    public void save(Iterable<AddPostDto> posts) {

        for (AddPostDto postDto : posts) {
            String userName = postDto.getUserName();
            String picturePath = postDto.getPictureName();

            User user = this.userRepository.findOneByUsername(userName);
            Picture pic = this.pictureRepository.findOneByPath(picturePath);

            Post post = DtoMappingUtil.convert(postDto, Post.class);

            if(user != null && pic != null){
                post.setPicture(pic);
                post.setUser(user);

                this.postRepository.saveAndFlush(post);
                System.out.println(String.format("Successfully imported Post %s.", post.getCaption()));
            }else{
                System.out.println("Error: Invalid data.");
            }

        }


    }

    @Override
    public List<UncomentedPostView> findAllUncomentedPosts() {
        List<Post> posts = this.postRepository.getAllUncumentedPosts();

        List<UncomentedPostView> postsToExport = DtoMappingUtil.convert(posts, UncomentedPostView.class);

        return postsToExport;
    }

}
