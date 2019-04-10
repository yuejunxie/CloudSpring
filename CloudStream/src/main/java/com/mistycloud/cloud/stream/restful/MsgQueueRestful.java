package com.mistycloud.cloud.stream.restful;

import com.mistycloud.cloud.stream.MsgChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/26 22:39
 * Description:
 */
@RequestMapping
@RestController
public class MsgQueueRestful {

    @Autowired
    private MsgChannel msgChannel;

    @RequestMapping
    public String send(String msg){
        Message<byte[]> message = MessageBuilder.withPayload(msg.getBytes()).build();
        msgChannel.readMsg().send(message);
        return "success";
    }

    public String vo(){
        return "";
    }

}
