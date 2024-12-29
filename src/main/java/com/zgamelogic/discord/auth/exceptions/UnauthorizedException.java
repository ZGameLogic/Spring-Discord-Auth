package com.zgamelogic.discord.auth.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
            super("Unauthorized");
        }
}