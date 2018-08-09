package com.elson.chapter1.hibernate.main;

import com.elson.chapter1.hibernate.po.TRole;
import com.elson.chapter1.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class HibernateExample {
    public static void main(String args[]) {
        Session session = null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            TRole role=(TRole)session.get(TRole.class, 2L);
            System.err.println("role_name=>" + role.getRoleName());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
