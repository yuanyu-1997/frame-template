package com.nobug.repository;

import com.nobug.entity.OperationLog;
import com.nobug.vo.OperationLogParameter;
import com.nobug.vo.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Objects;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OperationLogRepositoryTest {

    @Autowired
    OperationLogRepository opRepository;


    @Test
    public void test_() {
        OperationLogParameter opParam = new OperationLogParameter();
        opParam.setOperateUser("CZXIWADE");
        //parameter.setOperateMethod("selectOne");

        Specification<OperationLog> spec = (root, query, cb) -> {
            //
            Path<Object> user = root.get("operateUser");
            Path<Object> method = root.get("operateMethod");
            Path<Object> rClass = root.get("returnClass");
            Path<Object> costTime = root.get("costTime");
            //
            Predicate p1 = (opParam.getOperateUser() == null) ? null : cb.equal(user, opParam.getOperateUser());
            Predicate p2 = (opParam.getOperateMethod() == null) ? null : cb.equal(method, opParam.getOperateMethod());
            Predicate p3 = (opParam.getReturnClass() == null) ? null : cb.equal(rClass, opParam.getReturnClass());
            Predicate p4 = (opParam.getCostTime() == null) ? null : cb.equal(costTime, opParam.getCostTime());
            //
            Predicate[] predicates = Arrays.stream(new Predicate[]{p1, p2, p3, p4})
                    .filter(Objects::nonNull)
                    .toArray(Predicate[]::new);
            return cb.and(predicates);

        };
        Page<OperationLog> all = opRepository.findAll(spec, PageRequest.of(0, 10));
        //
        PageResult res = new PageResult();
        System.out.println(all.getTotalElements());
        System.out.println(all.getContent());


    }
}