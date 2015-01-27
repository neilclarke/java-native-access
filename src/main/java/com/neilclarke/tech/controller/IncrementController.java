package com.neilclarke.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neilclarke.tech.Incrementer;

@RestController
public class IncrementController {

	@Autowired(required = true)
	private Incrementer incrementer;

	@RequestMapping(value = "/increment/{number}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO incrementNumber(final @PathVariable Integer number) {
		return new ResponseDTO(incrementer.increment(number));
	}

}
