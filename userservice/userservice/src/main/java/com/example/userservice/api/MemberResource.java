package com.example.userservice.api;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Member;
import com.example.userservice.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberResource {
    private final MemberService memberService;

    @GetMapping("/user")
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok().body(memberService.getMembers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<Member>saveMember(@RequestBody Member member) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(memberService.saveMember(member));
    }
    @PostMapping("role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(memberService.saveRole(role));
    }
    @PostMapping("user/add-role-to-user")
    public ResponseEntity<?>saveRole(@RequestBody RoleToMemberForm form) {
        memberService.addRoleToMember(form.getRoleName(), form.getMemberName());
        return ResponseEntity.ok().build();
    }

    @Data
    class RoleToMemberForm {
        private String memberName;
        private String roleName;
    }
}
