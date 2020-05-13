package com.epam.events.Configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:Configuration.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("mainpage")
    String mainpage();

    @Key("filter.location")
    String location();

    @Key("filter.language")
    String language();

    @Key("search.keyword")
    String keyword();
}
