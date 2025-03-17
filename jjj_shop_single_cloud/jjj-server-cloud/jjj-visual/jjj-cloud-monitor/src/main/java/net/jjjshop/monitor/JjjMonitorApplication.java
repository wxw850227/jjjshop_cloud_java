package net.jjjshop.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 监控服务
 * @author jjj
 * @date: 2022/4/21 10:55
 */
@SpringBootApplication
@EnableAdminServer
@Slf4j
public class JjjMonitorApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(JjjMonitorApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application jjjshop is running! Access URLs:\n\t" +
                "Monitor: \thttp://" + ip + ":" + port   + "\n" +
                "----------------------------------------------------------");

    }
}
