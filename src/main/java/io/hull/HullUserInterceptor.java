package io.hull;

import io.hull.util.HullUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HullUserInterceptor extends AbstractInterceptor {


    public String intercept(ActionInvocation ai) throws Exception {

        ActionContext ctx = ai.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        Map<String,Object> session = ctx.getSession();

        String hullAppId      = "CHANGE_ME_APP_ID_HERE";
        String hullAppSecret  = "CHANGE_ME_APP_SECRET_HERE";
        String hullCookie     = "hull_" + hullAppId;

        String cookieVal  = HullUtils.getURLDecodedCookieValue(request.getCookies(), hullCookie);
        String userId = HullUtils.authenticateUser(cookieVal, hullAppSecret);

        session.put("HULL_USER_ID", userId);

        String result = ai.invoke();


        return result;
    }

}
