package com.project.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.moviebooking.entity.mappedclass.BaseEntity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "role_access")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleAccess extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_access_role_id"))
    Role role;

	@ManyToOne(targetEntity = Menu.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", foreignKey = @ForeignKey(name = "fk_role_access_menu_id"))
	Menu menu;

    @Column(name = "accesss")
    String accesss;

}
