package br.com.citrus.ticket.shared.utils;
import java.util.regex.Pattern;

class UUIDValidator {
    private static final String UUID_REGEX =
        "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

    public static boolean validateUUID(String uuid) {
        return UUID_PATTERN.matcher(uuid).matches();
    }
}

