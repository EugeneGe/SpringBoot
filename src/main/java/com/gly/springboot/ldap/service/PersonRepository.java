package com.gly.springboot.ldap.service;

import com.gly.springboot.ldap.entity.Person;
import org.springframework.data.ldap.repository.LdapRepository;

/**
 * @author EugeneGe
 * @date 2022-08-22 15:18
 */
public interface PersonRepository extends LdapRepository<Person> {
}
