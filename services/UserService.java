package app.services;

import app.dto.add.AddFollowerDto;
import app.dto.add.AddUserDto;
import app.dto.view.json.PopularUserView;
import app.dto.xml.UserWithPostCommentsView;

import java.util.List;

public interface UserService {

    void save(AddUserDto user);

    void save(Iterable<AddUserDto> users);

    void addFollowers(Iterable<AddFollowerDto> followerDtos);

    List<PopularUserView> findAllPopularUsers();

    List<UserWithPostCommentsView> findAllUsersWithMostCommentedPosts();

}
