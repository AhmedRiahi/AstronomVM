package com.astronomvm.simulator.configuration;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ClientInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        //Nothing to do
    }

}