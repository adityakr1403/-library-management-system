package com.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @Column(name = "memberId")
    private int memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "profession")
    private String profession;

    public Member() {

    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Member(int memberId, String name, String profession) {
        this.memberId = memberId;
        this.name = name;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
