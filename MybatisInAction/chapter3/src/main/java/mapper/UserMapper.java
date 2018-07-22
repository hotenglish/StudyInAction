package mapper;

import com.elson.chapter3.po.User;

public interface UserMapper {
    public User getUser(Long id);
    public int insertUser(User user);
}
