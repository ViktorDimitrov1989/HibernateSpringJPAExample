package app.services;

import app.dto.add.AddCommentDto;
import app.models.Comment;
import app.models.Post;
import app.models.User;
import app.repositories.CommentRepository;
import app.repositories.PostRepository;
import app.repositories.UserRepository;
import app.utils.DtoMappingUtil;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void save(AddCommentDto comment) {
    }

    @Override
    public void save(Iterable<AddCommentDto> comments) {

        for (AddCommentDto comment : comments) {
            String userName = comment.getUserName();

            if(comment.getPostDto() == null){
                System.out.println("Error: Invalid data.");
                return;
            }

            Integer postId = Integer.parseInt(comment.getPostDto().getId());

            User user = this.userRepository.findOneByUsername(userName);
            Post post = this.postRepository.findOne(postId);
            Comment commentToAdd = DtoMappingUtil.convert(comment, Comment.class);

            commentToAdd.setUser(user);
            commentToAdd.setPost(post);

            if(ValidationUtil.isValid(commentToAdd)){
                this.commentRepository.saveAndFlush(commentToAdd);
                System.out.println(String.format("Successfully imported Comment %s.", commentToAdd.getContent()));
            }else{
                System.out.println("Error: Invalid data.");
            }
        }


    }

}
