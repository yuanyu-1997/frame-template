package cn.yuanyu.guavatoken.interceptor;


import cn.yuanyu.guavatoken.enums.ResponseEnum;
import cn.yuanyu.guavatoken.utils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanyu
 */
@Slf4j
public abstract class AbstractInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResponseEnum result;
        try {
            result = preFilter(request);
        } catch (Exception e) {
            log.error("preHandle catch a exception:" + e.getMessage());
            result = ResponseEnum.FAIL;
        }
        if (ResponseEnum.SUCCESS.getCode().equals(result.getCode())) {
            return true;
        }
        handlerResponse(result, response);
        return false;
    }


    /**
     * 自定义pre处理
     */
    protected abstract ResponseEnum preFilter(HttpServletRequest request);

    /**
     * 错误处理事件
     */
    private void handlerResponse(ResponseEnum result, HttpServletResponse response){
        R res = new R();
        res.put("code", result.getCode())
                .put("status", result.getStatus())
                .put("message", result.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(mapper.writeValueAsString(res));
        } catch (Exception e) {
            log.error("handlerResponse catch a exception:" + e.getMessage());
        }
    }
}