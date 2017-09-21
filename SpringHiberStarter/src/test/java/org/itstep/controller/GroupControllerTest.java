package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupControllerTest {
	
	@MockBean
	GroupService groupService;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void createGroup(){

		Group group = new Group();
		group.setGroupName("J16");
		group.setCourse(2);
//		when(groupService.createAndUpdateGroup(group)).thenReturn(group);
//		when(groupService.isUnique(group)).thenReturn(true);
//		when(groupDAO.findOne(group.getGroupName())).thenReturn(null);
		RequestEntity<Group> reqEntity = null;
		try {
			reqEntity = new RequestEntity<Group>(group, HttpMethod.POST, new URI("/group"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Group> respEntyty = testRestTemplate.exchange(reqEntity, Group.class);
		assertEquals(HttpStatus.CREATED, respEntyty.getStatusCode());
		
		groupService.deleteGroup(group.getGroupName());
		
//		verify(groupService, Mockito.times(1)).createAndUpdateGroup(group);
	}
}