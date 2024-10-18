package com.mindex.challenge.data;

/**
 * Static classes to define the depth of the data returned.
 */
public class Views {

    /**
     * Summary will only return fields on the data object.
     */
    public static class Summary {
    }

    /**
     * Full includes Summary and adds in related objects.
     */
    public static class Full extends Summary {
    }
}
