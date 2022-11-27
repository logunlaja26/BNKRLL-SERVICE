package com.bnkrll.org.impl;

import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionService;

public class SessionServiceImpl {

    private SessionService sessionService;

    public SessionServiceImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    public Session getPlayerCurrentSession(String sessionId){
        Session response = new Session("12345");
        Session currSession = sessionService.getCurrentSession(sessionId); /** THis will be some type of database call**/
        return response;

    }


}
