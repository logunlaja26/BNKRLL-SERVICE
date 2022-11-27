package com.bnkrll.org.impl;

import com.bnkrll.org.impl.SessionServiceImpl;
import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class SessionImplTest {

    private SessionService sessionServiceMock;
    @Mock
    private Session session;

    @Before
    public void setup(){
        sessionServiceMock = mock(SessionService.class);
        session = new Session("12345");
    }


    @Test
    public void testGetPlayerCurrentSession_usingAMock(){
        when(sessionServiceMock.getCurrentSession(anyString())).thenReturn(session);
        SessionServiceImpl sessionServiceImpl = new SessionServiceImpl(sessionServiceMock);
        Session response = sessionServiceImpl.getPlayerCurrentSession(anyString());
        assertNotNull(response);
    }
}
