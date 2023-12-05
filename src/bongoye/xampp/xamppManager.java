package bongoye.xampp;

import java.io.IOException;


public class xamppManager {
    String xampPath = "e:\\xampp\\";

    public xamppManager() {
        try {

            ProcessBuilder processBuilder = new ProcessBuilder("E:\\xampp\\mysql_stop.bat");
            processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(processes.process[] p) {
        try {
            for (processes.process p1 : p) {
                ProcessBuilder processBuilder = new ProcessBuilder(xampPath + processToString(p1) + "_start.bat");
                processBuilder.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(processes.process[] p) {
        try {
            for (processes.process p1 : p) {
                String s1 = xampPath + processToString(p1) + "_stop.bat";
                ProcessBuilder processBuilder = new ProcessBuilder(s1);
                processBuilder.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String processToString(processes.process p) {
        String s1 = (p == processes.process.mysql) ? "mysql"
                : (p == processes.process.apache) ? "apache"
                        : (p == processes.process.filezilla) ? "filezilla"
                                : (p == processes.process.mercury) ? "mercury"
                                        : (p == processes.process.tomcat) ? "catalina" : null;

        return s1;
    }
}
