package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Member;

import java.util.List;

public interface MemberService {
    Member saveMember(Member member);
    Role saveRole(Role role);
    void addRoleToMember(String memberName, String roleName);
    Member getMember(String memberName);
    List<Member>getMembers();
}
