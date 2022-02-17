package com.codecool.memonyx.util;

import org.springframework.hateoas.server.core.SpringAffordanceBuilder;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class Utils {

    /** Generate url with controller class and id */
    public String urlCreator(Class controller, Long id) {
        try {
            URL myURL = new URL("http://localhost:8080/");
            return new URL(myURL, SpringAffordanceBuilder.DISCOVERER.getMapping(controller) + id).toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
