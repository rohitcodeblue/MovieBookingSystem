package com.project.moviebooking.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.moviebooking.constant.RoleTypeEnum;
import com.project.moviebooking.entity.mappedclass.BaseEntity;

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
@Table(name = "role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseEntity {

    static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    RoleTypeEnum type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    List<RoleAccess> roleAccessList;

//    @JoinColumn(name = "organization_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_role_organization_id"))
//    @ManyToOne(cascade = CascadeType.ALL)
//    Organization organizationId;

}
