package study.authorization.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import study.authorization.domain.login.SessionConst;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class loginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("인증 인터셉터 실행 [{}]", request.getRequestURI());

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.MEMBER_ID) == null) {

            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

   }
