package com.mistycloud.portal.vo;

import lombok.Data;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 23:10
 * Description:
 */
@Data
public class UserVO {

    private String userName;

    private String account;

    private String userId;

    public UserVO() {
    }

    public UserVO(String userName, String account, String userId) {
        this.userName = userName;
        this.account = account;
        this.userId = userId;
    }
}
