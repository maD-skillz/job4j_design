package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean getBool = true;
        byte getByte = 8;
        short getShort = 16;
        int getInt = 32;
        long getLong = 64;
        char getChar = '0';
        float getFloat = 32;
        double getDouble = 64.1;
        LOG.debug("Primitive types : {}", getBool);
        LOG.debug("Primitive types : {}", getByte);
        LOG.debug("Primitive types : {}", getShort);
        LOG.debug("Primitive types : {}", getInt);
        LOG.debug("Primitive types : {}", getLong);
        LOG.debug("Primitive types : {}", getChar);
        LOG.debug("Primitive types : {}", getFloat);
        LOG.debug("Primitive types : {}", getDouble);

    }
}
