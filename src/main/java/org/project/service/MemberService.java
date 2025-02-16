package org.project.service;

import org.project.model.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberService {
    private final Map<Integer, Member> memberMap;
    private AtomicInteger numMembers = new AtomicInteger(0); //uuid

    public MemberService() {

        this.memberMap = new HashMap<>();
    }

    public Member addMember(String name, int superCoins){
        int newVal = numMembers.incrementAndGet();
        Member member = new Member(newVal, name, superCoins);
        memberMap.put(newVal, member);
        System.out.println(name +" added successfully.");
        return member;
    }

    public Member getMemberById(int memberId){
        return memberMap.get(memberId);
    }
}
