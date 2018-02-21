package com.shinonometn.hacks.music.viewer.util;

import com.shinonometn.hacks.music.viewer.commons.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppCrashHandler implements Thread.UncaughtExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(AppCrashHandler.class);
    private final static AppCrashHandler instance = new AppCrashHandler();

    public static AppCrashHandler getInstance() {
        return instance;
    }

    private AppCrashHandler() {
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        logger.error(buildText(t,e), e);

    }

    public static String buildText(Thread t, Throwable e) {
        return new StringBuilder("\n")
                .append("--------------------------------------------").append("\n")
                .append("----------- <!> App Crashed <!> ------------").append("\n")
                .append("--------------------------------------------").append("\n")
                //TODO Change it before published
                .append("Version: ").append("1.0 ALPHA").append("\n")
                .append("   Time: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n")
                .append(" Thread: ").append(t.toString()).append("\n")
                .append("--------------------------------------------").append("\n")
                .append("-------- <i> System Information <i> --------").append("\n")
                .append("--------------------------------------------").append("\n")
                .append("     OS: ").append(Environment.OS.SYSTEM_VERSION).append("\n")
                .append(" Memory: ").append(Environment.OS.TOTAL_MEMORY).append("MB").append("\n")
                .append("   Java: ").append(System.getProperty("java.version")).append("\n")
                .append("    JVM: ").append(System.getProperty("java.vm.name")).append("\n")
                .append("         ").append(System.getProperty("java.jvm.db")).append("\n")
                .append("         ").append(System.getProperty("java.vm.vendor")).append("\n")
                .toString();
    }

}
