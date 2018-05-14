package work.yanghao.serviceimpl;

import work.yanghao.pojo.User;
import work.yanghao.service.UserService;

public class UserServiceImpl implements UserService{


    @Override
    public User login(String username, String password) {

        //模拟成功查询数据库返回一个查询结果
        User user = new User();
        user.setUserName("yanghao");
        user.setUserPassword("yanghao");
        return user;
    }
}
