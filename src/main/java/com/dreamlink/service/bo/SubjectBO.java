package com.dreamlink.service.bo;

import com.dreamlink.service.domain.Subject;
import com.dreamlink.service.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectBO {

    private final SubjectMapper subjectMapper;

    public List<Integer> getSubjectListByWelfareId(int welfareId) {
        return subjectMapper.selectSubjectListByWelfareId(welfareId);
    }

    public List<Integer> getWelfareIdByServiceId(int serviceId) {
        return subjectMapper.selectWelfareIdByServiceId(serviceId);
    }
}
