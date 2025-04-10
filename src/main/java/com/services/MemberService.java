package com.services;

import com.entities.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Member findById(Long id);
    boolean insertMember(String name, String email, String password);
    boolean updateMember(Long id, String firstname, String lastname, String username, String phone, String email, String description);
    boolean deleteMember(Long id);
    Member findByEmailAndPassword(String email, String password);
}
