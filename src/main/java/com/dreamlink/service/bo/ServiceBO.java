package com.dreamlink.service.bo;

import com.dreamlink.service.mapper.ServiceListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceBO {

    private final ServiceListMapper serviceListMapper;

    public List<com.dreamlink.service.domain.Service> getService() {
        return serviceListMapper.selectService();
    }
}
