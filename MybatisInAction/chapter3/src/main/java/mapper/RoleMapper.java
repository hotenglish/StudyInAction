package mapper;

import com.elson.chapter3.po.Role;

import java.util.List;

public interface RoleMapper {

    Role getRole(Long id);

    List<Role> findRole(String id);

    int deleteRole(Long id);

    int insertRole(Role role);

}
