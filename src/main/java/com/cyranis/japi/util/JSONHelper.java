package com.cyranis.japi.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class JSONHelper {

    /**
     * **************************************************************************************
     * attributes
     * **************************************************************************************
     */
//    private static final Logger LOGGER = Logger.getLogger(JSONHelper.class);

    /**
     * **************************************************************************************
     * constructors
     * **************************************************************************************
     */

    /**
     * **************************************************************************************
     * methods
     * **************************************************************************************
     */
    /**
     * Parses a String into a JsonNode (Jackson library) object
     * @param response
     * @return
     */
    public static JsonNode stringToJson(String response)
    {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(response);
        }
        catch (IOException e) {
//            LOGGER.error("e", e);
        }
        return null;
    }

    /**
     * Used in the JAR projects
     * @param inputStream
     * @return
     */
    public static JsonNode fileToJsonNode(InputStream inputStream)
    {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(inputStream);
        }
        catch (IOException e) {
//            LOGGER.error("e", e);
        }
        return null;
    }

    /**
     * Used in the WAR projects (should only be one method, but it does not work)
     * @param fileName
     * @return
     */
    public static JsonNode fileToJsonNode(String fileName)
    {
        final ObjectMapper mapper = new ObjectMapper();
        final URL resourceUrl = JSONHelper.class.getClassLoader().getResource(fileName);
        try {
            return mapper.readTree(new File(resourceUrl.toURI()));
        }
        catch (IOException e) {
//            LOGGER.error("e", e);
        }
        catch (URISyntaxException e) {
//            LOGGER.error("e", e);
        };
        return null;
    }

    public static boolean isJSONValid(String jsonInString) {

        if (null != jsonInString) {
            try {
                final ObjectMapper mapper = new ObjectMapper();
                mapper.readTree(jsonInString);
                return true;
            }
            catch (IOException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * **************************************************************************************
     * getters & setters
     * **************************************************************************************
     */
}