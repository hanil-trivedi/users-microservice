package com.hanil.demo.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	//filter all except field1 , field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveFilteredBean() {
		 FilteredBean filteredBean = new FilteredBean("value1","value2","value3");
		 
		 //dynamic response filtering
		 MappingJacksonValue mapping = new MappingJacksonValue(filteredBean);
		 SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		//create filter
		 FilterProvider filters = new SimpleFilterProvider().addFilter("FilteredBeanFilter", filter);
		 mapping.setFilters(filters);
		 
		 return mapping;
	}
	
	//filter all except field2 , field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveFilteredBeanList() {
		List<FilteredBean> list = Arrays.asList(new FilteredBean("value1","value2","value3"),new FilteredBean("value11","value12","value13") );
		
		 //dynamic response filtering
		 MappingJacksonValue mapping = new MappingJacksonValue(list);
		 SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		//create filter
		 FilterProvider filters = new SimpleFilterProvider().addFilter("FilteredBeanFilter", filter);
		 mapping.setFilters(filters);
		
		return mapping;
	}
}
