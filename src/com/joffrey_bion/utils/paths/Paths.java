package com.joffrey_bion.utils.paths;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Paths {

    /**
     * Returns the path to the directory containing the Jar that contains the
     * specified class.
     * 
     * @param clazz
     *            The class to look for.
     * @return The path to the parent directory of the Jar file containing the
     *         specified class, or {@code null} if this code is not run
     *         from a Jar file.
     * @throws MalformedURLException If an error occurs.
     * @throws URISyntaxException If an error occurs.
     */
    public static String getJarLocation(Class<?> clazz) throws MalformedURLException,
            URISyntaxException {
        String urlString = ClassLoader.getSystemClassLoader().getResource(
                clazz.getName().replace('.', '/') + ".class").toString();
        int exclMarkIndex = urlString.indexOf('!');
        if (exclMarkIndex != -1) {
            urlString = urlString.substring(urlString.indexOf("file:"), exclMarkIndex);
            File file = new File(new URL(urlString).toURI());
            return file.getParent();
        } else {
            System.err.println("Calling getJarLocation() while not running a Jar file.");
            return null;
        }
    }
    
    public static String relativize(String baseDir, String absolutePath) {
        java.nio.file.Path abs = java.nio.file.Paths.get(absolutePath);
        java.nio.file.Path base = java.nio.file.Paths.get(baseDir);
        return base.relativize(abs).toString();
    }
    
    public static String relativizeSibling(String baseFile, String absolutePath) {
        java.nio.file.Path abs = java.nio.file.Paths.get(absolutePath);
        java.nio.file.Path base = java.nio.file.Paths.get(baseFile).getParent();
        return base.relativize(abs).toString();
    }

    public static String resolve(String baseDir, String relativePath) {
        java.nio.file.Path base = java.nio.file.Paths.get(baseDir);
        return base.resolve(relativePath).toString();
    }

    public static String resolveSibling(String baseFile, String relativePath) {
        java.nio.file.Path base = java.nio.file.Paths.get(baseFile);
        return base.resolveSibling(relativePath).toString();
    }

    /**
     * Fix problems in the URIs (spaces for instance).
     * 
     * @param uri
     *            The original URI.
     * @return The corrected URI.
     */
    public static String fixURI(String uri) {
        // handle platform dependent strings
        String path = uri.replace(java.io.File.separatorChar, '/');
        // Windows fix
        if (path.length() >= 2) {
            char ch1 = path.charAt(1);
            // change "C:blah" to "/C:blah"
            if (ch1 == ':') {
                char ch0 = Character.toUpperCase(path.charAt(0));
                if (ch0 >= 'A' && ch0 <= 'Z') {
                    path = "/" + path;
                }
            }
            // change "//blah" to "file://blah"
            else if (ch1 == '/' && path.charAt(0) == '/') {
                path = "file:" + path;
            }
        }
        // replace spaces in file names with %20.
        // Original comment from JDK5: the following algorithm might not be
        // very performant, but people who want to use invalid URI's have to
        // pay the price.
        int pos = path.indexOf(' ');
        if (pos >= 0) {
            StringBuilder sb = new StringBuilder(path.length());
            // put characters before ' ' into the string builder
            for (int i = 0; i < pos; i++)
                sb.append(path.charAt(i));
            // and %20 for the space
            sb.append("%20");
            // for the remaining part, also convert ' ' to "%20".
            for (int i = pos + 1; i < path.length(); i++) {
                if (path.charAt(i) == ' ')
                    sb.append("%20");
                else
                    sb.append(path.charAt(i));
            }
            return sb.toString();
        }
        return path;
    }

}
