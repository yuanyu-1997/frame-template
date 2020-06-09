package com.nobug.service;


import com.nobug.entity.OperationLog;
import com.nobug.repository.OperationLogRepository;
import com.nobug.vo.OperationLogParameter;
import com.nobug.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Service
public class OperationLogService {

    @Autowired
    private OperationLogRepository opRepository;

    public void insert(OperationLog operationLog) {
        opRepository.save(operationLog);
    }

    /**
     * @param opParam 查询条件
     * @param pageNum 查询的页码
     * @param rows    每页查询的条数
     */
    public PageResult findByPage(OperationLogParameter opParam, Integer pageNum, Integer rows) {
        log.debug("opParam:{},pageNum:{},rows:{}", opParam, pageNum, rows);
        Specification<OperationLog> spec = (root, query, cb) -> {
            //
            Path<Object> user = root.get("operateUser");
            Path<Object> method = root.get("operateMethod");
            Path<Object> rClass = root.get("returnClass");
            Path<Object> costTime = root.get("costTime");
            //
            Predicate p1 = (StringUtils.isEmpty(opParam.getOperateUser())) ? null : cb.equal(user, opParam.getOperateUser());
            Predicate p2 = (StringUtils.isEmpty(opParam.getOperateMethod())) ? null : cb.equal(method, opParam.getOperateMethod());
            Predicate p3 = (StringUtils.isEmpty(opParam.getReturnClass())) ? null : cb.equal(rClass, opParam.getReturnClass());
            Predicate p4 = (StringUtils.isEmpty(opParam.getCostTime())) ? null : cb.gt(costTime.as(Long.class), opParam.getCostTime());
            //
            Predicate[] predicates = Arrays.stream(new Predicate[]{p1, p2, p3, p4})
                    .filter(Objects::nonNull)
                    .toArray(Predicate[]::new);
            log.debug("predicates.length:{},predicates:{}", predicates.length, predicates);
            return cb.and(predicates);

        };
        long startTime = System.currentTimeMillis();
        Page<OperationLog> all = opRepository.findAll(spec, PageRequest.of(pageNum, rows));
        long endTime = System.currentTimeMillis();
        log.debug("Count Cost Time : {} ms", endTime - startTime);
        //
        PageResult res = new PageResult();
        res.setTotal(all.getTotalElements());
        res.setDataList(all.getContent());
        return res;
    }

}
