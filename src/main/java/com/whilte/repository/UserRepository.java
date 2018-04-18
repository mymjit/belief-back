package com.whilte.repository;

import com.whilte.domail.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository< User , Long > {

    List< User > getByTelephoneNumber( String telephoneNumber);

    User getUserByTelephoneNumber(String telephoneNumber);
}
