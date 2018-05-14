package work.yanghao.service;


import work.yanghao.pojo.User;

/**
 * 用户服务接口
 */
public interface UserService {

    //用户登录
    public User login(String username,String password);
}
