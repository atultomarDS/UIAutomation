package helpers;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonHelper {

    public static final Logger logger = Logger.getLogger(JsonHelper.class);

    public static InputStreamReader getJsonRequest(String filepath) {
        InputStream is = JsonHelper.class.getClassLoader().getResourceAsStream(filepath);
        if (is ==null) {
            throw new IllegalArgumentException("Inputstream not loaded , filepath [" + filepath + "] seems incorrect. Please check") ;
        }
        logger.info("Trying to fetch file -> " + filepath);
        return  new InputStreamReader(is);

    }
}
