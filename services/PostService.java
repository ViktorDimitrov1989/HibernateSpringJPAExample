package app.services;
import app.dto.add.AddPostDto;
import app.dto.view.json.UncomentedPostView;

import java.util.List;

public interface PostService {

    void save(AddPostDto post);

   void save(Iterable<AddPostDto> posts);

   List<UncomentedPostView> findAllUncomentedPosts();

}
