package com.bnkrll.org.controller;

import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;



public class ControllersTest {


    private SessionService sessionServiceMock;

    @Before
    public void setup(){
        sessionServiceMock = mock(SessionService.class);
    }

    @Test
    public void test(){
        SessionService sessionServiceMock = mock(SessionService.class);
        List<Session> listOfSessions = sessionServiceMock.getCurrentSession(2);
        //assertTrue(true);

    }
}
