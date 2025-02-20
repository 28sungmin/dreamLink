package com.dreamlink.service.mapper;

import com.dreamlink.service.domain.Service;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceListMapper {

    public List<Service> selectService();

    public List<String> selectServiceListByServiceId(int serviceId);

    public String selectSubjectBySubjectId(int subjectId);

    public int selectServiceIdByService(String service);
}
