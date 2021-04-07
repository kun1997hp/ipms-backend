package com.viettel.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "account", schema = "IPMS", catalog = "library_management")
public class Account {
    /// <summary>
    /// The id of the account
    /// </summary>
    /// <example>1</example>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    /// <summary>
    /// The email of the account
    /// </summary>
    /// <example>phuocnn3@fpt.edu.vn</example>
    @Basic
    @Column(name = "email")
    private String email;

    /// <summary>
    /// The password of the account
    /// </summary>
    /// <example>123a@123</example>
    @Basic
    @Column(name = "password")
    private String password;

}
