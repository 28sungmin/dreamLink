package com.dreamlink.service.mapper;

import com.dreamlink.service.domain.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectMapper {

    public List<Integer> selectSubjectListByWelfareId(int welfareId);

    public List<Integer> selectWelfareIdByServiceId(int serviceId);
}
