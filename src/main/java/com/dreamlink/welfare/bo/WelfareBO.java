package com.dreamlink.welfare.bo;

import com.dreamlink.service.bo.ServiceBO;
import com.dreamlink.service.bo.ServiceListBO;
import com.dreamlink.service.bo.SubjectBO;
import com.dreamlink.welfare.domain.Welfare;
import com.dreamlink.welfare.mapper.WelfareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WelfareBO {

    private final ServiceBO serviceBO;
    private final ServiceListBO serviceListBO;
    private final SubjectBO subjectBO;
    private final WelfareMapper welfareMapper;
    private final static int POST_MAX_SIZE = 4; // 이런 것도 원래는 따로 페이징 관련 클래스를 만들어서 거기에 넣는게 더 좋다.

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

    public List<Welfare> getWelfareAllList(Integer prevId, Integer nextId) {

        Integer standardId = null; // 기준 postId(prev or next)

        if (prevId != null) { // 3) 경우
            standardId = prevId;

            // 예) [5 6 7]
            List<Welfare> welfareList = welfareMapper.selectWelfareListPrev(standardId, POST_MAX_SIZE);

            // reverse List
            Collections.reverse(welfareList);
            return welfareList;
        } else if (nextId != null) { // 2) 경우
            standardId = nextId;

            List<Welfare> welfareList = welfareMapper.selectWelfareListNext(standardId, POST_MAX_SIZE);
            return welfareList;
        }

        return welfareMapper.selectWelfareList(standardId, POST_MAX_SIZE);
    }

    // 이전 없나?
    // user가 쓴 글들 중 제일 큰 숫자가 prevId와 같으면 이전이 없는 것임
    public boolean isPrevLastPage(int prevId) {
        int maxPostId = welfareMapper.selectWelfareIdAsSort("desc");
        return maxPostId == prevId; // true이면 마지막이라는 뜻.
    }

    // 다음 없나?
    // user가 쓴 글들 중 제일 작은 숫자가 nextId와 같으면 다음이 없는 것임
    public boolean isNextLastPage(int nextId) {
        int minPostId = welfareMapper.selectWelfareIdAsSort("asc");
        return minPostId == nextId; // true이면 마지막이라는 뜻.
    }

    public List<List<String>> getSubjectAllList() {
        List<Welfare> welfareList = welfareMapper.selectWelfareAllList();

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
}
