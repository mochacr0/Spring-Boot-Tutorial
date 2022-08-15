package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Member;
import com.example.userservice.repository.MemberRepository;
import com.example.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImplement implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public Member saveMember(Member member) {
        log.info("Adding user {} ", member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Adding role {} ", role);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToMember(String memberName, String roleName) {
        log.info("Adding role {} to user {} ", roleName, memberName);
        Member member = memberRepository.findByMemberName(memberName);
        Role role = roleRepository.findByName(roleName);
        member.getRoles().add(role);
    }

    @Override
    public Member getMember(String memberName) {
        log.info("Finding user {} ", memberName);
        return memberRepository.findByMemberName(memberName);
    }

    @Override
    public List<Member> getMembers() {
        log.info("Finding all users");
        return memberRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        else {
            log.info("User found {} ", memberName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        member.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority((role.getName())));
        });

        return new org.springframework.security.core.userdetails.User(member.getMemberName(), member.getPassword(), authorities);
    }
}
