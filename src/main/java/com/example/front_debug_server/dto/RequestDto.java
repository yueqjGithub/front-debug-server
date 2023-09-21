package com.example.front_debug_server.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class RequestDto {
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    private String serverId;

    private String extraData;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @NotNull(message = "客户端时间不能为空")
    private Long clientTime;
}
