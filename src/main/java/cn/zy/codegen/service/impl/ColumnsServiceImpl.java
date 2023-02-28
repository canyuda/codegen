package cn.zy.codegen.service.impl;

import cn.zy.codegen.dao.entity.Columns;
import cn.zy.codegen.dao.mapper.ColumnsMapper;
import cn.zy.codegen.service.ColumnsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ColumnsServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements ColumnsService {

}
