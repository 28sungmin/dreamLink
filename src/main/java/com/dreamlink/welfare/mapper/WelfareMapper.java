package com.dreamlink.welfare.mapper;

import com.dreamlink.welfare.domain.Welfare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WelfareMapper {

    public List<Welfare> selectWelfareListByEntity(String entity);

    public Welfare selectWelfareByWelfareId(int welfareId);

    public List<Welfare> selectWelfareListByEntityService(
            @Param("entity") String entity,
            @Param("service") String service);

    public List<Integer> selectWelfareIdListByEntity(String entity);
}
