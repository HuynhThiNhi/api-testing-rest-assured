package common.utils;

import common.config.Config;
import org.apache.commons.codec.binary.Base64;

public class Utils {

    //static public String encodeCredentials(email, token) {
    static public String encodeCredentials() {
        byte[] credentials = (Config.email + ':' + Config.apiToken).getBytes();
        return new String(Base64.encodeBase64(credentials));
    }

}
