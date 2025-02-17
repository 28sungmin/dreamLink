package com.dreamlink.welfare.bo;

import com.dreamlink.service.bo.ServiceBO;
import com.dreamlink.service.bo.ServiceListBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WelfareBO {

    private final ServiceBO serviceBO;
    private final ServiceListBO serviceListBO;

    public List<com.dreamlink.service.domain.Service> getService() {
        return serviceBO.getService();
    }

    public List<String> getServiceListByServiceId(int serviceId) {
        return serviceListBO.getServiceListByServiceId(serviceId);
    }
}
