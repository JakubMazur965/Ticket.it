package com.example.ticket_it.components;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-local.properties")
public class SSHTunnel {

    // db parameters - URL for DataBase
    private static String dataSourceSshUrl;
    private static String dataSourceSshUser;
    private static String dataSourceSshPassword;
    private static String dataSourcePassword;

    @Autowired
    public SSHTunnel(@Value("${spring.datasource.ssh.url}") String dataSourceSshUrl,
                     @Value("${spring.datasource.ssh.user}") String dataSourceSshUser,
                     @Value("${spring.datasource.ssh.password}") String dataSourceSshPassword,
                     @Value("${spring.datasource.password}") String dataSourcePassword) {
        setDataSourceSshUrl(dataSourceSshUrl);
        setDataSourceSshUser(dataSourceSshUser);
        setDataSourceSshPassword(dataSourceSshPassword);
        setDataSourcePassword(dataSourcePassword);
    }

    @Value("${spring.datasource.ssh.url}")
    public void setDataSourceSshUrl(String dataSourceSshUrl) {
        SSHTunnel.dataSourceSshUrl = dataSourceSshUrl;
    }

    @Value("${spring.datasource.ssh.user}")
    public void setDataSourceSshUser(String dataSourceSshUser) {
        SSHTunnel.dataSourceSshUser = dataSourceSshUser;
    }

    @Value("${spring.datasource.ssh.password}")
    public void setDataSourceSshPassword(String dataSourceSshPassword) {
        SSHTunnel.dataSourceSshPassword = dataSourceSshPassword;
    }

    @Value("${spring.datasource.password}")
    public void setDataSourcePassword(String dataSourcePassword) {
        SSHTunnel.dataSourcePassword = dataSourcePassword;
    }

    public Session startTunnel() throws JSchException {
        // parameters for SSH Tunnel
        String[] sshUrlParts = dataSourceSshUrl.split("/");
        String[] sshUrlHost = sshUrlParts[2].split(":");
        String sshLocalHost = sshUrlHost[0];
        String sshHost = "pluton.kt.agh.edu.pl";
        int sshLocalPort = Integer.parseInt(sshUrlHost[1]);
        int sshPort = 22;

        // Starting SSH Tunnel
        JSch jSch = new JSch();
        Session session = jSch.getSession(dataSourceSshUser, sshHost, sshPort);
        session.setPassword(dataSourceSshPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(sshLocalPort, sshLocalHost, sshLocalPort);

        // return a ssh session
        return session;
    }

    public Connection connectionToDataBase() {
        // connection to the DataBase
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataSourceSshUrl, dataSourceSshUser, dataSourcePassword);
            System.out.println("Connection has been established successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
