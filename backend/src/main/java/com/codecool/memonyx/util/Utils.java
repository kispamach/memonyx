package com.codecool.memonyx.util;

import org.springframework.hateoas.server.core.SpringAffordanceBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    /** Generate url with controller class and id */
    public static String urlCreator(Class controller, Long id) {
        try {
            URL myURL = new URL("http://localhost:8080/");
            return new URL(myURL, SpringAffordanceBuilder.DISCOVERER.getMapping(controller) + id).toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
