package com.example.front_debug_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.front_debug_server.dto.RequestDto;
import com.example.front_debug_server.http.HttpError;
import com.example.front_debug_server.http.HttpResp;
import com.example.front_debug_server.model.Record;
import com.example.front_debug_server.service.RecordService;
import com.example.front_debug_server.utils.ZzBeanCopierUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {
    @Resource
    RecordService recordService;

    @PostMapping("")
    public HttpResp<?> addRecord (@Validated @RequestBody RequestDto dto) {
        Record copy = ZzBeanCopierUtils.copy(dto, new Record());
        recordService.save(copy);
        return HttpResp.ok();
    }

    @DeleteMapping("/clear")
    public HttpResp<?> deleteRecord () {
        recordService.remove(new QueryWrapper<>());
        return HttpResp.ok();
    }

    @GetMapping("")
    public HttpResp<List<Record>> getRecord () {
        List<Record> list = recordService.list();
        return HttpResp.ok(recordService.list());
    }
}
