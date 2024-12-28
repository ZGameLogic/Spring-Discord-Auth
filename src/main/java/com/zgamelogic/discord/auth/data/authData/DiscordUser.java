package com.zgamelogic.discord.auth.data.authData;

public record DiscordUser(
        String locale,
        boolean verified,
        String username,
        String global_name,
        String avatar,
        Long id
) {}