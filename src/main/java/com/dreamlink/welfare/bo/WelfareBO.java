package com.dreamlink.welfare.bo;

import com.dreamlink.service.bo.ServiceBO;
import com.dreamlink.service.bo.ServiceListBO;
import com.dreamlink.service.bo.SubjectBO;
import com.dreamlink.welfare.domain.Welfare;
import com.dreamlink.welfare.mapper.WelfareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WelfareBO {

    private final ServiceBO serviceBO;
    private final ServiceListBO serviceListBO;
    private final SubjectBO subjectBO;
    private final WelfareMapper welfareMapper;

    public List<com.dreamlink.service.domain.Service> getService() {
        return serviceBO.getService();
    }

    public List<String> getServiceListByServiceId(int serviceId) {
        return serviceListBO.getServiceListByServiceId(serviceId);
    }

    public List<Welfare> getWelfareListByEntity(String entity) {
        return welfareMapper.selectWelfareListByEntity(entity);
    }

    public List<List<String>> getSubjectList(String entity) {
        List<Welfare> welfareList = welfareMapper.selectWelfareListByEntity(entity);

        if (welfareList.isEmpty() == false) {
            List<List<String>> subjectList = new ArrayList<>();
            for (Welfare welfare : welfareList) {
                List<String> welfareSubjectList = new ArrayList<>();
                List<Integer> welfareSubjectId = subjectBO.getSubjectListByWelfareId(welfare.getId());
                for (Integer subjectId : welfareSubjectId) {
                    String welfareSubject = serviceListBO.getSubjectBySubjectId(subjectId);
                    welfareSubjectList.add(welfareSubject);
                }
                subjectList.add(welfareSubjectList);
            }
            return subjectList;
        }
        return null;
    }

    public List<Welfare> getServiceListByService(String entity, String service) {
        // entity: 중앙부처, service: 아동
        // entity가 중앙부처인 리스트 아이디들 모두 가져옴.
        List<Integer> entityWelfareIdList = welfareMapper.selectWelfareIdListByEntity(entity);

        int serviceId = serviceListBO.getServiceIdByService(service); // 3
        List<Integer> welfareIdList = subjectBO.getWelfareIdByServiceId(serviceId);

        entityWelfareIdList.retainAll(welfareIdList);
        System.out.println(entityWelfareIdList);
        List<Welfare> entityWelfareList = new ArrayList<>();
        for (Integer entityWelfareId : entityWelfareIdList) {
            entityWelfareList.add(welfareMapper.selectWelfareByWelfareId(entityWelfareId));
        }

        return entityWelfareList;
    }

    public List<List<String>> getSubjectListBySelectedService(List<Welfare> selectedService) {
        if (selectedService.isEmpty() == false) {
            List<List<String>> subjectList = new ArrayList<>();
            for (Welfare welfare : selectedService) {
                List<String> welfareSubjectList = new ArrayList<>();
                List<Integer> welfareSubjectId = subjectBO.getSubjectListByWelfareId(welfare.getId());
                for (Integer subjectId : welfareSubjectId) {
                    String welfareSubject = serviceListBO.getSubjectBySubjectId(subjectId);
                    welfareSubjectList.add(welfareSubject);
                }
                subjectList.add(welfareSubjectList);
            }
            return subjectList;
        }

        return null;
    }
}
