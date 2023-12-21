package com.vku.bocuoi.yu.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@Table(name = "STUDENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity implements UserDetails {
    @Id
    @Column(name = "STUDENT_ID", nullable = false)
    private String studentId;

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    private String studentName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;

    @Column(name = "GENDER")
    private Boolean gender;

    @Column(name = "CID", nullable = false, length = 13)
    private String cid;

    @Temporal(TemporalType.DATE)
    @Column(name = "CID_DATE")
    private Date cidDate;

    @Column(name = "CID_PLACE", length = 100)
    private String cidPlace;

    @Column(name = "HOMETOWN")
    private String hometown;

    @Column(name = "ADDRESS_PERMANENT")
    private String addressPermanent;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "NATION")
    private String nation;

    @Column(name = "RELIGION")
    private String religion;

    @Column(name = "EDUCATIONAL_LEVEL")
    private String educationalLevel;

    @Column(name = "QUALIFICATION")
    private String qualification;

    @Column(name = "POLITICAL_THEORY")
    private String politicalTheory;

    @Column(name = "DATE_IN")
    private Date dateIn;

    @Column(name = "PLACE_IN")
    private String placeIn;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE", nullable = false, length = 10)
    private String phone;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "RESET_PASSWORD_CODE")
    private String resetPasswordCode;

    @Column(name = "RESET_PASSWORD_EXPIRY")
    private Date resetPasswordExpiry;

//    @JsonBackReference
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "STUDENT_UNIT_ROLE",
//            joinColumns = @JoinColumn(name = "STUDENT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ORGANIZATION_ID"))
//    private Organization organizationSet;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "UNIT_ID", nullable = false)
    private Unit unit;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "STUDENT_ROLE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roleSet = new LinkedHashSet<>();

//    @OneToMany(mappedBy = "student")
//    private Set<Volunteer> volunteerSet = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "student")
//    private Set<Feedback> feedbackSet = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roleSet) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getId())));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return studentId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
