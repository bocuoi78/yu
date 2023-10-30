package com.vku.bocuoi.yu.model.entity;

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
@Table(name = "STUDENT")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity implements UserDetails {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SID", unique = true, nullable = false)
    private String sId;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "GENDER")
    private Boolean gender;

    @Column(name = "NATION", length = 100)
    private String nation;

    @Column(name = "CID", nullable = false, length = 13)
    private String cId;

    @Temporal(TemporalType.DATE)
    @Column(name = "CID_DATE")
    private Date cIdDate;

    @Column(name = "CID_PLACE", length = 100)
    private String cIdPlace;

    @Column(name = "PHONE", nullable = false, length = 10)
    private String phone;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "RELIGION", length = 100)
    private String religion;

    @Column(name = "EDUCATIONAL_LEVEL", length = 100)
    private String educationalLevel;

    @Column(name = "QUALIFICATION", length = 100)
    private String qualification;

    @Column(name = "POLITICAL_THEORY", length = 100)
    private String politicalTheory;

    @Column(name = "ADDRESS_PERMANENT", length = 500)
    private String addressPermanent;

    @Column(name = "ADDRESS_TEMPORARY", length = 500)
    private String addressTemporary;

    @Column(name = "DATE_IN")
    private Date dateIn;

    @Column(name = "PLACE_IN", length = 100)
    private String placeIn;

    @Column(name = "IMAGE", length = 256)
    private String image;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password = "Abc@123";

    @OneToMany(mappedBy = "student")
    private Set<StudentUnit> studentUnitSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Volunteer> volunteerSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Feedback> feedbackSet = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return sId;
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
