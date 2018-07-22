package mapper;

import com.elson.chapter3.po.Role;

public interface RoleMapper {

    Role getRole(Long id);

    Role findRole(String id);

    int deleteRole(Long id);

    int insertRole(Role role);

}
