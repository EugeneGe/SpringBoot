package com.gly.springboot.ldap.service.impl;

import com.gly.springboot.controller.RedisTestController;
import com.gly.springboot.ldap.entity.Person;
import com.gly.springboot.ldap.service.LdapService;
import com.gly.springboot.ldap.service.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * @author EugeneGe
 * @date 2022-08-22 15:24
 */
@Service
public class LdapServiceImpl implements LdapService {

    private final static Logger logger = LoggerFactory.getLogger(LdapServiceImpl.class);

    private final LdapTemplate ldapTemplate;
    private final PersonRepository personRepository;

    @Autowired
    public LdapServiceImpl(LdapTemplate ldapTemplate, PersonRepository personRepository) {
        this.ldapTemplate = ldapTemplate;
        this.personRepository = personRepository;
    }

    @Override
    public Iterable<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findOnePerson(String attribute, String value) {
        try {
            return ldapTemplate.findOne(query().where(attribute).is(value), Person.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Found 0 result, error: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Person authenticate(String attribute, String username, String password) {
        return ldapTemplate.authenticate(
                query().where(attribute).is(username),
                password,
                (dirContext, ldapEntryIdentification) ->
                        ldapTemplate.findOne(query().where(attribute).is(username), Person.class));
    }

}
