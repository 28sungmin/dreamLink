package com.dreamlink.welfare.mapper;

import com.dreamlink.welfare.domain.Welfare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WelfareMapper {

    public List<Welfare> selectWelfareListByEntity(String entity);

    public Welfare selectWelfareByWelfareId(int welfareId);

    public List<Integer> selectWelfareIdListByEntity(String entity);

    public List<Welfare> selectWelfareAllList();

    public List<Welfare> selectWelfareList(
            @Param("standardId") Integer standardId,
            @Param("limit") int limit);

    public List<Welfare> selectWelfareListPrev(
            @Param("standardId") Integer standardId,
            @Param("limit") int limit);

    public List<Welfare> selectWelfareListNext(
            @Param("standardId") Integer standardId,
            @Param("limit") int limit);

    public int selectWelfareIdAsSort(String sort);


}
