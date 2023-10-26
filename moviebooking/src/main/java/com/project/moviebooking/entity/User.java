package com.project.moviebooking.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.project.moviebooking.constant.RoleTypeEnum;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Rohit
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "active")
    Boolean active;
    
    @Column(name = "name")
    String name;

    @Column(name = "deleted")
    Boolean deleted = Boolean.FALSE;

    @Size(min = 1, max = 255)
    @Column(name = "password")
    String password;

    @Size(min = 1, max = 255)
    @Column(name = "email_id")
    String emailId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mobile_no")
    String mobileNo;

    @Column(name = "whatsapp_no")
    String whatsappNo;
        
    @Column(name = "contact_pref_whatsapp")
    Boolean contactPrefWhatsapp;
    
    @Column(name = "contact_pref_call")
    Boolean contactPrefCall;

    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    UserDetail userDetail;

    @ColumnDefault(value = "0")
    @Column(name = "balance_reward_points")
    Long balanceRewardPoints;

    @ColumnDefault(value = "0")
    @Column(name = "redeemed_reward_point")
	Long redeemedRewardPoint;
    
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private RoleTypeEnum roleType;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRole> userRoleList = new ArrayList<>();

    public User(Long id) {
        this.setId(id);
    }
}
