package cn.zy.codegen.service.impl;

import cn.zy.codegen.dao.entity.Statistics;
import cn.zy.codegen.dao.mapper.StatisticsMapper;
import cn.zy.codegen.service.StatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

}
