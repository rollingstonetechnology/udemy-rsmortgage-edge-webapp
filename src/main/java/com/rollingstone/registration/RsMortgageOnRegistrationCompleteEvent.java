package com.rollingstone.registration;


import org.springframework.context.ApplicationEvent;

import com.rollingstone.persistence.model.RsMortgageUser;

public class RsMortgageOnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final RsMortgageUser user;

    public RsMortgageOnRegistrationCompleteEvent(final RsMortgageUser user, final String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public RsMortgageUser getUser() {
        return user;
    }

}
