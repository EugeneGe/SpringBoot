package com.gly.springboot.ldap.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.lang.model.element.Name;

/**
 * @author EugeneGe
 * @date 2022-08-22 15:15
 */
@Data
@Entry(base = "dc=test,dc=com", objectClasses = "user")
public class Person {

    @Id
    private Name id;
    @Attribute(name = "cn")
    private String cn;
    @Attribute(name = "sn")
    private String sn;
    @Attribute(name = "mail")
    private String mail;
    @Attribute(name = "telephoneNumber")
    private String telephoneNumber;
    @Attribute(name = "description")
    private String description;
    @Attribute(name = "givenName")
    private String givenName;

}
