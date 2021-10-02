package action;

import bean.User;
import dao.UserDAO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Author Lemon
 * @Date 2021/6/24 10:42
 * @Version 1.0
 */

public class addUser  {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String execute()throws Exception{
        Resource resource = new ClassPathResource("WEB-INF/applicationContext.xml");//获取配置文件
        BeanFactory factory = new XmlBeanFactory(resource);
        UserDAO userDAO = (UserDAO)factory.getBean("userDAO");//获取UserDAO
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDAO.insert(user);
        return "s";
    }
}
