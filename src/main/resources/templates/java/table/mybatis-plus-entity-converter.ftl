package ${converterPackage};

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ${entityPackage}.${entityName};
import ${voPackage}.${voName};
import java.util.List;

<#assign at="@">
${at}Mapper
public interface ${converterName} {

    ${converterName} INSTANCE = Mappers.getMapper(${converterName}.class);

    ${entityName} toDao(${voName} vo);

    ${voName} toVo(${entityName} entity);

    List<${entityName}> toDaoList(List<${voName}> voList);

    List<${voName}> toVoList(List<${entityName}> entityList);
}
