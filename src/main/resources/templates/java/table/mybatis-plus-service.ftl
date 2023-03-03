package ${servicePackage};

import com.baomidou.mybatisplus.extension.service.IService;
import ${entityPackage}.${entityName};
import ${voPackage}.${voName};
import cn.zy.common.page.PageData;
import cn.zy.common.page.PageRequest;
import java.util.List;

public interface ${serviceName} extends IService<${entityName}>{

	/**
	 * 添加或者更新
	 * @param vo
	 */
	void addOrUpdate(${voName} vo);

	${voName} getOneByUniq(${voName} vo);

	${voName} getOneById(Long id);

	void upsert(${voName} vo);

	void upsert(List<${voName}> list);

	/**
	 * 分页查询
	 * @param request
	 */
	PageData<List<${voName}>> selectPage(PageRequest<${voName}> request);

	void delete(Long id);
	<#list columnsList as col><#if col.columnName='status'>
	void updateStatus(Long id, ${col.columnType} status);
	</#if></#list>
	List<${voName}> selectList(${voName} vo);

    void delBatch(List<Long> idList);

    /**
     * 批量新增
     *
     * @param ids
     * @return
     */
    void insertBatch(List<${voName}> list);

}