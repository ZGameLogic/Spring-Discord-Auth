package com.zgamelogic.discord.auth.data.authData;

public record DiscordLoginPayload(
        DiscordToken token,
        DiscordUser user
) {}