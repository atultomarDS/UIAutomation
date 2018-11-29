package helpers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProperties extends Properties {

    public ProjectProperties() {
        super();
    }

    public void loadProperties(String filename) {
        InputStream istream = null;

        try {
            // first look for file as a resource
            istream = ClassLoader.getSystemResourceAsStream(filename);

            // if not found, look for it as a file system file
            if (istream == null) {
                File file = new File(filename);
                if (!file.exists()) {
                    throw new IllegalArgumentException("properties file not found: " + filename);
                }
                istream = FileUtils.openInputStream(file);
            }
            super.load(istream);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            IOUtils.closeQuietly(istream);
        }
    }

    public String getRequiredProperty(String propertyName) {
        String value = findProperty(propertyName);
        if (value == null) {
            throw new IllegalStateException("Required property not defined: " + propertyName);
        }
        return value;
    }

    public String findProperty(String propertyName) {
        String value = System.getProperty(propertyName);
        if (value == null) {
            value = super.getProperty(propertyName);
        }
        if (value != null) {
            value = value.trim();
        }
        return value;
    }
}

