package com.bnkrll.org.controller;

import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class ControllersTest {


    private SessionController sessionController;

    @Mock
    private Session session;

    @Before
    public void setup(){
        sessionController = mock(SessionController.class);
    }

    @Test
    public void testGetSession(){
        Session response = sessionController.getSession("testSessionId");
        assertEquals(session,response);
    }
}
