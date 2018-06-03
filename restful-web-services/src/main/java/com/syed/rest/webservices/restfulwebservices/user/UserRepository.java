/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sammar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
