package io.hull;

import io.hull.util.HullUtils;

import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HullUserInterceptor extends AbstractInterceptor {

    private static ObjectMapper mapper = new ObjectMapper();
    private static String hullAppId      = "CHANGE-ME";
    private static String hullAppSecret  = "CHANGE-ME";
    private static String hullOrgUrl     = "https://CHANGE-ME.hullapp.io";
    private static HullClient hullClient = new HullClient(hullAppId, hullAppSecret, hullOrgUrl);

    public String intercept(ActionInvocation ai) throws Exception {

        ActionContext ctx = ai.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        Map<String,Object> session = ctx.getSession();

        String hullCookie     = "hull_" + hullAppId;

        String cookieVal  = HullUtils.getURLDecodedCookieValue(request.getCookies(), hullCookie);
        String userId = HullUtils.authenticateUser(cookieVal, hullAppSecret);


        if (userId == null) {
            session.put("HULL_USER_ID", null);
            session.put("HULL_USER", null);
        } else {
            String sessionUser = (String) session.get("HULL_USER_ID");

            if (sessionUser != userId) {
                session.put("HULL_USER_ID", userId);
                session.put("HULL_USER", this.getHullUser(userId));
            }
        }


        String result = ai.invoke();

        return result;
    }

    private HullUser getHullUser(String userId) {
        String userData = hullClient.get(userId);
        return new HullUser(userData);
    }



}
