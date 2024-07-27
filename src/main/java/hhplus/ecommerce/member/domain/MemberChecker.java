package hhplus.ecommerce.member.domain;

import org.springframework.stereotype.Component;

@Component
public class MemberChecker {

    public boolean checkById(String userId) {
        return userId != null && userId.matches("\\d+");
    }
}