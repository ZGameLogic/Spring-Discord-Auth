package com.zgamelogic.discord.auth.resolvers;

import com.zgamelogic.discord.auth.data.authData.DiscordUser;
import com.zgamelogic.discord.auth.data.database.authData.AuthData;
import com.zgamelogic.discord.auth.data.database.authData.AuthDataRepository;
import com.zgamelogic.discord.auth.services.DiscordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class DiscordUserResolver implements HandlerMethodArgumentResolver {
    private final DiscordService discordService;
    private final AuthDataRepository authDataRepository;

    public DiscordUserResolver(DiscordService discordService, AuthDataRepository authDataRepository) {
        this.discordService = discordService;
        this.authDataRepository = authDataRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return DiscordUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String token = request.getHeader("token");
        String device = request.getHeader("device");

        if (token == null || device == null) throw new UnauthorizedException();
        Optional<DiscordUser> discordUser = discordService.getUserFromToken(token);
        if (discordUser.isEmpty()) throw new UnauthorizedException();
        Optional<AuthData> authData = authDataRepository.findById_DiscordIdAndId_DeviceIdAndToken(discordUser.get().id(), device, token);
        if (authData.isEmpty()) throw new UnauthorizedException();

        model.addAttribute("discordUser", discordUser.get());
        model.addAttribute("authData", authData.get());
        return null;
    }
}
