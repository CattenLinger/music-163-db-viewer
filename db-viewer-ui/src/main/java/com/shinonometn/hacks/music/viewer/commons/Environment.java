package com.shinonometn.hacks.music.viewer.commons;

import com.shinonometn.hacks.music.viewer.utils.ReflectionHelper;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.util.Locale;

public class Environment {

    public enum OS {
        /**
         * Microsoft Windows.
         */
        WINDOWS("windows"),
        /**
         * Linux and Unix like OS, including Solaris.
         */
        LINUX("linux"),
        /**
         * Mac OS X.
         */
        OSX("osx"),
        /**
         * Unknown operating system.
         */
        UNKNOWN("universal");

        private final String checkedName;

        OS(String checkedName) {
            this.checkedName = checkedName;
        }

        public String getCheckedName() {
            return checkedName;
        }

        /**
         * The current operating system.
         */
        public static final OS CURRENT_OS;

        /**
         * The total memory/MB this computer have.
         */
        public static final int TOTAL_MEMORY;

        public static final String PATH_SEPARATOR = File.pathSeparator;
        public static final String FILE_SEPARATOR = File.separator;
        public static final String LINE_SEPARATOR = System.lineSeparator();

        /**
         * The system default encoding.
         */
        public static final String ENCODING = System.getProperty("sun.jnu.encoding", Charset.defaultCharset().name());

        /**
         * The version of current operating system.
         */
        public static final String SYSTEM_VERSION = System.getProperty("os.version");

        /**
         * The architecture of current operating system.
         */
        public static final String SYSTEM_ARCHITECTURE;

        static {
            String name = System.getProperty("os.name").toLowerCase(Locale.US);
            if (name.contains("win"))
                CURRENT_OS = WINDOWS;
            else if (name.contains("mac"))
                CURRENT_OS = OSX;
            else if (name.contains("solaris") || name.contains("linux") || name.contains("unix") || name.contains("sunos"))
                CURRENT_OS = LINUX;
            else
                CURRENT_OS = UNKNOWN;

            //Method method = sun.management.OperatingSystemImpl.class

            Object bytes = ReflectionHelper.call(ManagementFactory.getOperatingSystemMXBean(), "getTotalPhysicalMemorySize");


            if (bytes instanceof Long)
                TOTAL_MEMORY = (int) (((Long) bytes) / 1024 / 1024);
            else
                TOTAL_MEMORY = 1024;

            String arch = System.getProperty("sun.arch.data.model");
            if (arch == null)
                arch = System.getProperty("os.arch");
            SYSTEM_ARCHITECTURE = arch;
        }
    }


    public static File getAppDataDir(String folder){
        String homeDir = System.getProperty("user.home",".");

        switch (OS.CURRENT_OS){

            case WINDOWS:
                return new File(homeDir,String.format(".local/share/%s/",folder));
            case LINUX:
                String appData = System.getenv("APPDATA");
                return new File(appData != null ? appData : homeDir, String.format(".%s/",folder));
            case OSX:
                return new File(homeDir,"Library/Application Support/" + folder);
            default:
                return new File(homeDir,String.format("%s/",folder));
        }

    }

    public static void setClipboard(String string) {
        ClipboardContent c = new ClipboardContent();
        c.putString(string);
        Clipboard.getSystemClipboard().setContent(c);
    }
}
