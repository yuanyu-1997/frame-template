package cn.yuanyu.guavatoken.interceptor;


import cn.yuanyu.guavatoken.enums.ResponseEnum;
import cn.yuanyu.guavatoken.utils.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
    private void handlerResponse(ResponseEnum result, HttpServletResponse response) {
        R res = new R();
        res.put("code", result.getCode())
                .put("status", result.getStatus())
                .put("message", result.getMessage());
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(res));
        } catch (Exception e) {
            log.error("handlerResponse catch a exception:" + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}