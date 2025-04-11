package com.zgamelogic.discord.auth.data.authData;

import com.zgamelogic.discord.auth.data.database.authData.AuthData;

public record DeviceRegistration(String notificationId, AuthData.DeviceType deviceType) {
}
