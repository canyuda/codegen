package cn.zy.codegen.service.impl;

import cn.zy.codegen.dao.entity.Tables;
import cn.zy.codegen.dao.mapper.TablesMapper;
import cn.zy.codegen.service.TablesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TablesServiceImpl extends ServiceImpl<TablesMapper, Tables> implements TablesService {

}
