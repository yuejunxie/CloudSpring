package com.mistycloud.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/26 22:47
 * Description:
 */
public interface MsgChannel {

    @Input("cloud_stream")
    SubscribableChannel readMsg();
}
