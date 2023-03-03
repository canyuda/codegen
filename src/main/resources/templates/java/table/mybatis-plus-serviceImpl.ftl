package ${serviceImplPackage};

import cn.hutool.core.collection.CollUtil;
import cn.zy.common.exception.BizTipException;
import cn.zy.common.page.PageData;
import cn.zy.common.page.PageRequest;
import ${voPackage}.${voName};
import ${entityPackage}.${entityName};
import ${mapperPackage}.${mapperName};
import ${converterPackage}.${converterName};
import ${servicePackage}.${serviceName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${serviceImplName} extends ServiceImpl<${mapperName}, ${entityName}> implements ${serviceName} {

    <#if uniqIndexModels??&&(uniqIndexModels?size>0)>
    <#list uniqIndexModels as item>
    /**
    * 通过唯一索引获取
    * @param entity
    * @return
    */
    private ${entityName} ${item.methodName}(${entityName} entity) {
        <#list item.fields as field>
        if (entity.get${field?cap_first}() == null) {
            throw BizTipException.getInst("字段未填:${field?cap_first}");
        }
        </#list>
        ${entityName} temp = new ${entityName}();
        <#list item.fields as field>
        temp.set${field?cap_first}(entity.get${field?cap_first}());
        </#list>
        Wrapper<${entityName}> queryWrapper = new QueryWrapper<>(temp);
        return getOne(queryWrapper);
    }
    </#list>
    </#if>

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(${voName} vo) {
        ${entityName} entity = ${converterName}.INSTANCE.toDao(vo);
        validate(entity);
        saveOrUpdate(entity);
    }

    private void validate(${entityName} entity) {
        <#if uniqIndexModels??&&(uniqIndexModels?size>0)>
        ${entityName} old;
        <#list uniqIndexModels as item>
        old = ${item.methodName}(entity);
        if (entity.getId() == null && old != null) {
            //新增
            throw BizTipException.getInst("已存在相同的数据");
        }

        if (entity.getId() != null && old != null && old.getId().longValue() != entity.getId().longValue()) {
            //更新,修改改与本值之外的记录重复
            throw BizTipException.getInst("已存在相同的数据");
        }
        </#list>
        </#if>
    }

    @Override
    public ${voName} getOneByUniq(${voName} vo) {
        // 转换
        ${entityName} entity = ${converterName}.INSTANCE.toDao(vo);
        ${entityName} one = getOne(entity);
        return ${converterName}.INSTANCE.toVo(one);
    }

    @Override
    public ${voName} getOneById(Long id) {
        ${entityName} entity = getById(id);
        return ${converterName}.INSTANCE.toVo(entity);
    }

    @Override
    public void upsert(${voName} vo) {
        // 转换
        ${entityName} entity = ${converterName}.INSTANCE.toDao(vo);
        upsert(entity);
    }

    private void upsert(${entityName} entity) {
        if (entity == null) {
            return;
        }
        if (entity.getId() == null) {
            ${entityName} old = getOne(entity);
            if (old != null) {
                entity.setId(old.getId());
            }
        }
        saveOrUpdate(entity);
    }

    private ${entityName} getOne(${entityName} entity) {
    <#if uniqIndexModels??&&(uniqIndexModels?size>0)>
        ${entityName} old;
        <#list uniqIndexModels as item>
            try{
                old=${item.methodName}(entity);
                if(old!=null){
                    return old;
                }
            }catch(BizTipException e){
                log.error("BizTipException ",e);
            }
        </#list>
    </#if>
        return null;
    }

    @Override
    public void upsert(List<${voName}> list) {
        // 转换
        List<${entityName}> entityList = ${converterName}.INSTANCE.toDaoList(list);
        for (${entityName} entity : entityList) {
            upsert(entity);
        }
    }

    @Override
    public PageData<List<${voName}>> selectPage(PageRequest<${voName}> request) {
        // 转换
        ${entityName} entity = ${converterName}.INSTANCE.toDao(request.getData());
        // 查询
        Page<${entityName}> page = new Page<>(request.getPage(), request.getPageSize());
        Wrapper<${entityName}> queryWrapper = new QueryWrapper<>(entity);
        page.addOrder(OrderItem.descs(${entityName}.COL_ID));
        Page<${entityName}> result = getBaseMapper().selectPage(page, queryWrapper);
        // 封装结果
        PageData<List<${voName}>> pageResult = new PageData<>();
        pageResult.setData(${converterName}.INSTANCE.toVoList(result.getRecords()));
        pageResult.setTotal(result.getTotal());
        return pageResult;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        ${entityName} entity = new ${entityName}();
        entity.setId(id);
        entity.setDeleted(true);
        updateById(entity);
    }

    @Override
<#list columnsList as col><#if col.columnName='status'>
    public void updateStatus(Long id, ${col.columnType} status) {
</#if></#list>
        if (id == null || status == null) {
            return;
        }
        ${entityName} entity = new ${entityName}();
        entity.setId(id);
        entity.setStatus(status);
        updateById(entity);
    }

    @Override
    public List<${voName}> selectList(${voName} vo) {
        // 转换
        ${entityName} entity = ${converterName}.INSTANCE.toDao(vo);
        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>(entity);
        queryWrapper.orderByDesc(${entityName}.COL_ID);
        List<${entityName}> list = list(queryWrapper);
        return ${converterName}.INSTANCE.toVoList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatch(List<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        // 转换
        List<${entityName}> updateEntityList = idList.stream().map(item -> {
            ${entityName} entity = new ${entityName}();
            entity.setId(item);
            return entity;
        }).collect(Collectors.toList());
        updateBatchById(updateEntityList, 200);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<${voName}> voList) {
        if (CollUtil.isEmpty(voList)) {
            return;
        }
        // 转换
        List<${entityName}> entityList = ${converterName}.INSTANCE.toDaoList(voList);
        saveBatch(entityList, 200);
    }
}