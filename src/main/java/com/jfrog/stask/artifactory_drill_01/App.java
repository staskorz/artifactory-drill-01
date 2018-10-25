package com.jfrog.stask.artifactory_drill_01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jfrog.stask.artifactory_drill_lib_01.Lib01;


public class App {
    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {
        logger.info("BEGIN");
        
        System.out.println("Hello World!");
        System.out.println("Adding 4 to 5, result: " + Lib01.add(4, 5));
        
        logger.info("END");
    }
}
