package com.mistycloud.cloud.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/26 23:54
 * Description:
 */
public interface OutChannel {
    @Output("cloud_stream")
    SubscribableChannel sendMsg();
}
