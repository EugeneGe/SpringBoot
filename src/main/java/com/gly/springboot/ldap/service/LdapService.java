package com.gly.springboot.ldap.service;

import com.gly.springboot.ldap.entity.Person;

/**
 * @author EugeneGe
 * @date 2022-08-22 15:23
 */
public interface LdapService {

    /**
     * 获取所有用户
     *
     * @return list {@link Person}
     */
    Iterable<Person> findAllPersons();

    /**
     * 获取单个用户
     *
     * @param attribute 属性
     * @param value     值
     * @return {@link Person}
     */
    Person findOnePerson(String attribute, String value);

    /**
     * 验证的属性
     *
     * @param attribute 属性
     * @param username  用户名
     * @param password  密码
     * @return {@link Person}
     */
    Person authenticate(String attribute, String username, String password);

}

