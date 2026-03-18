package com.zgamelogic.discord.auth.data.authData;

public record DiscordUser(
        String locale,
        String username,
        String global_name,
        String avatar,
        Long id
) {}