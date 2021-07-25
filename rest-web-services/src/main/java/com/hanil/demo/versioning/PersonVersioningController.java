package com.hanil.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	//URI versioning using 2 urls v1/person and v2/person
	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Hanil Trivedi");
	}
	
	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Hanil","Trivedi"));
	}
	
	//Request Parameter versioning using request params inside requestMapping
	@GetMapping(value="person/param" , params="version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Hanil Trivedi");
	}
	
	@GetMapping(value="person/param" , params="version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Hanil","Trivedi"));
	}
	
	
	//header versioning using request headers(instead of params) inside requestMapping
	@GetMapping(value="person/header" , headers="X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Hanil Trivedi");
	}
	
	@GetMapping(value="person/header" , headers="X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Hanil","Trivedi"));
	}
	
	//'Produces' versioning using produces (instead of params/headers) inside requestMapping
	//this is also called content-negotiation or "Accept versioning" or "MIME/Media type versioning" and its also based on headers 
		@GetMapping(value="person/produces" , produces="Application/vnd.company.app-v1+json")
		public PersonV1 getPersonProducesV1() {
			return new PersonV1("Hanil Trivedi");
		}
		
		@GetMapping(value="person/produces" , produces="Application/vnd.company.app-v2+json")
		public PersonV2 getPersonProducesV2() {
			return new PersonV2(new Name("Hanil","Trivedi"));
		}
}
