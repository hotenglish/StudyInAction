package com.elson.chapter9.main;

import com.elson.chapter9.mapper.RoleMapperChapter9;
import com.elson.chapter9.pojo.Role;
import com.elson.chapter9.util.QueryParams;
import com.elson.chapter9.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public class C9PagingPluginMain {

    public static void main(String args[]) throws Exception {
        //queryWithRowBounds();
        queryWithPaginPlugin();
    }

    private static void queryWithRowBounds(){
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        RoleMapperChapter9 roleMapper = session.getMapper(RoleMapperChapter9.class);
        List<Role> roleList = roleMapper.findRolesByName("testName", new RowBounds(0,5));
        for (Role role : roleList) {
            System.out.println("role_no=>" + role.getId() + "\t role_name=>" + role.getRoleName());
        }
    }

    private static void queryWithPaginPlugin(){
        SqlSession session = SqlSessionFactoryUtil.openSqlSession();
        RoleMapperChapter9 roleMapper = session.getMapper(RoleMapperChapter9.class);
        QueryParams queryParams=new QueryParams();
        queryParams.setCheckFlag(true);
        queryParams.setPage(1);
        queryParams.setPageSize(5);
        queryParams.setUserFlag(true);
        queryParams.setRoleName("testName");
        List<Role> roleList = roleMapper.findRolesByName(queryParams);
        for (Role role : roleList) {
            System.out.println("role_no=>" + role.getId() + "\t role_name=>" + role.getRoleName());
        }
    }
}
