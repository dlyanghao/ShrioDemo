package work.yanghao.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyAuthorizationFilter extends AuthorizationFilter{

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        //获取配置文件中声明的权限列表
        String[] perms = (String[])o;
        //获取主体
        Subject subject = getSubject(servletRequest, servletResponse);
        //改变授权逻辑（权限有其中之一也可以访问资源）
        if(perms==null||perms.length==0)
        {
            return true;
        }
        for (String perm : perms) {
            if(subject.isPermitted(perm))
            {
                return true;
            }
        }
        return false;
    }
}
