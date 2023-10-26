package com.project.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.project.moviebooking.constant.MenuEnum;
import com.project.moviebooking.entity.mappedclass.BaseEntity;

import lombok.NoArgsConstructor;

/**
 *
 * @author Rohit
 */
@Entity
@Table(name = "menu")
@NoArgsConstructor
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "title")
    private String title;

    @Column(name = "url_path")
    private String urlPath;

    @Column(name = "icon")
    private String icon;

    @NotNull
    @Column(name = "display_order")
    private Integer displayOrder;

    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne
    private Menu parentId;

    @Column(name = "master_menu")
    private Boolean masterMenu = Boolean.FALSE;

    @Column(name = "actions")
    private String actions;
    
    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private MenuEnum code;

    public Menu(Long id) {
        this.setId(id);
    }

}
