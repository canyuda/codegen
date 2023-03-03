package ${mapperPackage};

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${entityPackage}.${entityName};

@Mapper
public interface ${mapperName} extends BaseMapper<${entityName}> {

}
