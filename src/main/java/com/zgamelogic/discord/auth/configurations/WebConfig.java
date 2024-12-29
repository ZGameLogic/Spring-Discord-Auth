package com.zgamelogic.discord.auth.configurations;

import com.zgamelogic.discord.auth.resolvers.DiscordUserResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class WebConfig implements WebMvcConfigurer {
    private final DiscordUserResolver discordAuthUserResolver;

    public WebConfig(DiscordUserResolver discordAuthUserResolver) {
        this.discordAuthUserResolver = discordAuthUserResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(discordAuthUserResolver);
    }
}
