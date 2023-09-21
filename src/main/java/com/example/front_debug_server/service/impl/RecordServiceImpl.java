package com.example.front_debug_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.front_debug_server.mapper.RecordMapper;
import com.example.front_debug_server.model.Record;
import com.example.front_debug_server.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
}
