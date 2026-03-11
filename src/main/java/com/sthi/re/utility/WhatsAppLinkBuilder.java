package com.sthi.re.utility;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WhatsAppLinkBuilder {

    public static String generateLink(String advisorNumber, String message) {
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        return "https://wa.me/" + advisorNumber + "?text=" + encodedMessage;
    }
}
