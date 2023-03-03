package cn.zy.codegen.service.impl;

import cn.zy.codegen.dao.entity.Columns;
import cn.zy.codegen.dao.mapper.ColumnsMapper;
import cn.zy.codegen.service.ColumnsService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ColumnsServiceImpl extends ServiceImpl<ColumnsMapper, Columns> implements ColumnsService {

    @Override
    public void testWrapper() {
        LambdaQueryWrapper<Columns> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Columns::getTableName, "recruit_apply").eq(Columns::getTableSchema, "oa-recruit");
        System.out.println(wrapper.getSqlSelect());
        System.out.println(wrapper.getSqlComment());
        System.out.println(wrapper.getSqlFirst());
        System.out.println(wrapper.getTargetSql());
        System.out.println(wrapper.getCustomSqlSegment());
        System.out.println(wrapper.getSqlSet());
        System.out.println(wrapper.getSqlSegment());
        Map<String, Object> paramNameValuePairs = wrapper.getParamNameValuePairs();

        List<Columns> list = list(wrapper);
        System.out.println(JSON.toJSONString(list));
    }
}
