package com.example.front_debug_server.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("debug_record")
public class Record {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String userName;

    private String roleId;

    private String serverId;

    private String roleName;

    private String extraData;

    private LocalDateTime createTime;

    private Long clientTime;
}
