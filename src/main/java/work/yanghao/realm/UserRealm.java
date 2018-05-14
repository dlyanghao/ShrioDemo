package work.yanghao.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import work.yanghao.pojo.User;
import work.yanghao.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm{

    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public UserService getUserService() {
        return userService;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Shrio执行授权方法");
        //获取当前主体的primaryPrincipal（当前主体已经被认证）
        String primaryPrincipal = (String)principals.getPrimaryPrincipal();
        System.out.println("当前已经登录认证的用户是"+primaryPrincipal);
        //根据身份primaryPrincipal获取权限信息，通常这里查询数据库，这里模拟当前用户的权限查到为用户
        //封装简单的授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        ArrayList<String> permisions = new ArrayList<>();
        permisions.add("用户");
        permisions.add("管理员");
        simpleAuthorizationInfo.addStringPermissions(permisions);
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("Shrio执行认证方法");
        //得到要认证串的用户名和密码
        String username = ((UsernamePasswordToken)token).getUsername();
        String password = new String(((UsernamePasswordToken) token).getPassword());
        //调用业务层查询数据库
        User user = userService.login(username, password);
        if(user!=null)
        {
            //principle 主体/用户名
            //credentials 密码
            //realmName realm名称
            return new SimpleAuthenticationInfo(username,password,"UserRealm");
        }
        System.out.println("用户名或者密码错误");
        return null;
    }
}
