package com.powernode;

import com.powernode.server.MessageService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Application implements ApplicationRunner {
    @Resource
    private MessageService messageService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 程序一启动就会运行该方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMsg();
    }
}