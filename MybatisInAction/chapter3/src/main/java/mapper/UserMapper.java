package mapper;

import com.elson.chapter3.po.User;

public interface UserMapper {

    User getUser(Long id);

    int insertUser(User user);

}
