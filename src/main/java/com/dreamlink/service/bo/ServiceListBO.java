package com.dreamlink.service.bo;

import com.dreamlink.service.domain.Service;
import com.dreamlink.service.mapper.ServiceListMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceListBO {

    private final ServiceListMapper serviceListMapper;

    public List<Service> getService() {
        return serviceListMapper.selectService();
    }

    public List<String> getServiceListByServiceId(int serviceId) {
        return serviceListMapper.selectServiceListByServiceId(serviceId);
    }

    public String getSubjectBySubjectId(int subjectId) {
        return serviceListMapper.selectSubjectBySubjectId(subjectId);
    }

    public int getServiceIdByService(String service) {
        return serviceListMapper.selectServiceIdByService(service);
    }
}
