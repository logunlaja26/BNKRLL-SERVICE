package com.bnkrll.org.impl;

import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SessionImplTest {

    @Mock
    private SessionService sessionServiceMock;
    @InjectMocks
    private SessionServiceImpl sessionServiceImpl;
    @Mock
    private Session session;

    @Test
    public void testGetPlayerCurrentSession_usingAMock(){
        when(sessionServiceMock.getCurrentSession(anyString())).thenReturn(session);
        Session response = sessionServiceImpl.getPlayerCurrentSession(anyString());
        verify(sessionServiceMock,times(1)).getCurrentSession(anyString());
        assertNotNull(response);
    }
}
