package com.hlfront.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 贾佳
 * @date 2023/3/7 15:24
 */

@Data
@Accessors(chain = true)
@TableName("t_front_user")
public class TFrontUser {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("user_name")
    private String userName;

    @TableField("user_avatar")
    private String userAvatar;

    @TableField("user_email")
    private String userEmail;

    @TableField("user_wechatId")
    private String userWechatId;
}
