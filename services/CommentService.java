package app.services;
import app.dto.add.AddCommentDto;

public interface CommentService {

    void save(AddCommentDto comment);

   void save(Iterable<AddCommentDto> comments);

}
