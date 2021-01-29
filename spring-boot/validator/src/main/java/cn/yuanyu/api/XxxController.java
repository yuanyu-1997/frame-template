package cn.yuanyu.api;

import cn.yuanyu.dto.XxxWrapper;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/xxx")
@Api(tags = "测试@Valid")
public class XxxController {



    @PostMapping("/notNull")
    @ApiOperation(value = "NotNull", response = XxxWrapper.NotNullDTO.class)
    public XxxWrapper.NotNullDTO notNull(@Valid @RequestBody XxxWrapper.NotNullDTO notNullDTO) {

        String dto = JSON.toJSONString(notNullDTO, true);
        System.out.println(dto);

        return notNullDTO;
    }

    @PostMapping("/notEmpty")
    @ApiOperation(value = "NotNull", response = XxxWrapper.NotEmptyDTO.class)
    public XxxWrapper.NotEmptyDTO notEmpty(@Valid @RequestBody XxxWrapper.NotEmptyDTO notEmptyDTO) {

        String dto = JSON.toJSONString(notEmptyDTO, true);
        System.out.println(dto);

        return notEmptyDTO;
    }

    @PostMapping("/notBlank")
    @ApiOperation(value = "NotBlank", response = XxxWrapper.NotBlankDTO.class)
    public XxxWrapper.NotBlankDTO notBlank(@Valid @RequestBody XxxWrapper.NotBlankDTO notBlankDTO) {

        String dto = JSON.toJSONString(notBlankDTO, true);
        System.out.println(dto);

        return notBlankDTO;
    }


    @PostMapping("/page")
    @ApiOperation(value = "搜索", response = Map.class)
    public Map<String, String> search(@Valid @RequestBody XxxWrapper.XxxMapSearchDTO searchDTO, BindingResult result) {
        //判断校验是否存在错误
        //if (result.hasErrors()) {
        //    System.out.println(result);
        //    return;
        //}

        String dto = JSON.toJSONString(searchDTO, true);
        System.out.println(dto);

        return null;
    }

}