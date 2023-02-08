package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper extends ModelMapper{
	
	 public <D> List<D> mapList(List<? extends Object> source, Class<D> destinationType) {
		 List<D> tmpSource =new ArrayList<D>();
		 tmpSource=source
			.stream()
			.map(element -> super
			.map(element, destinationType))
			.collect(Collectors.toList());
       return tmpSource;
   }

}
