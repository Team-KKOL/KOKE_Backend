package com.koke.koke_backend.common.initializer;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Properties;

import static java.lang.System.exit;

@Slf4j
@Profile("local")
@Component
@ConfigurationProperties(prefix = "ssh")
@Validated
@Setter
public class SSHTunnelInitializer {

    @NotNull
    private String remoteJumpHost;

    @NotNull
    private String user;

    @NotNull
    private Integer sshPort;

    @NotNull
    private String privateKey;

    @NotNull
    private String databaseHost;

    @NotNull
    private Integer databasePort;

    private Session session;

    @PreDestroy
    public void closeSSH() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public Integer buildSSHConnection() {
        Integer forwardedPort = null;

        try {
            log.info("================= start ssh tunneling... =================");
            JSch jSch = new JSch();

            log.info("================= creating ssh session =================");
            jSch.addIdentity(privateKey); // Private Key

            session = jSch.getSession(user, remoteJumpHost, sshPort); // 세션 설정

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            log.info("================= complete creating ssh session =================");

            log.info("================= starting connecting ssh connection =================");
            session.connect(); // ssh 연결
            log.info("================= success connecting ssh connection  =================");

            log.info("start forwarding");
            forwardedPort = session.setPortForwardingL(8800, databaseHost, databasePort);
            log.info("successFully connected to database");
        } catch (Exception e) {
            log.error("fail to make ssh tunneling");
            this.closeSSH();
            e.printStackTrace();
            exit(1);
        }

        return forwardedPort;
    }

}
