package hhplus.ecommerce.member.interceptor;

import hhplus.ecommerce.member.domain.MemberChecker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class MemberInterceptor implements HandlerInterceptor {

    private final MemberChecker memberChecker;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String memberId = request.getHeader("MEMBER_ID");
        if (memberId == null || !memberChecker.checkById(memberId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "id가 유효하지 않습니다");
            return false;
        }
        return true;
    }
}
