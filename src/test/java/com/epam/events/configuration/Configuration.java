package com.epam.events.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:Configuration.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("mainpage")
    String mainpage();

    @Key("endpoint.events")
    String events();

    @Key("endpoint.talkLibrary")
    String talkLibrary();
}
