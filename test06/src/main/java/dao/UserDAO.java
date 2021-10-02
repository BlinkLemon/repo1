package dao;

import bean.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @Author Lemon
 * @Date 2021/6/24 10:41
 * @Version 1.0
 */
public class UserDAO extends HibernateDaoSupport {

    //保存用户的方法
    public void insert(User user){
        this.getHibernateTemplate().save(user);
    }
}
