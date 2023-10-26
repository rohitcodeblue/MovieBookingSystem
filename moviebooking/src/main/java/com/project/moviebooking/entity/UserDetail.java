package com.project.moviebooking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author a594444
 */
@Entity
@Table(name = "user_detail")

@NoArgsConstructor
@Getter
@Setter
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne()
    private User userId;

    @Size(min = 1, max = 255)
    @Column(name = "email_id")
    private String emailId;

    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private Integer age;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mobile_no")
    private String mobileNo;

    @Size(min = 1, max = 10)
    @Column(name = "sex")
    private String sex;

    @Size(max = 10)
    @Column(name = "language")
    private String language;

    @Size(max = 50)
    @Column(name = "mobile_details")
    private String mobileDetails;

    @Size(max = 20)
    @Column(name = "occupation")
    private String occupation;

    @Column(name = "profile_pic_path")
    private String profilePicPath;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
}
