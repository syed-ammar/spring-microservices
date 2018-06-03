/**
 * 
 */
package com.syed.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sammar
 *
 */
@RestController
public class PersonVersionController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Syed Ammar");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Syed","Ammar"));
	}
	
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Syed Ammar");
	}
	
	@GetMapping(value="person/param",params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Syed","Ammar"));
	}
	
	@GetMapping(value="person/param", headers="version=1")
	public PersonV1 headerV1() {
		return new PersonV1("Syed Ammar");
	}
	
	@GetMapping(value="person/param",headers="version=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Syed","Ammar"));
	}
}
