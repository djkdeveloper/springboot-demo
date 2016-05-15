package com.djk.controller;

import com.djk.annotation.NeedIntercept;
import com.djk.bean.BaseResponse;
import com.djk.service.HellWorldService;
import com.djk.utils.MessageSourceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author djk
 * @date 2016/5/9
 */
@Api("djk测试接口")
@RestController
public class HelloWorldController {

    @Autowired
    private HellWorldService hellWorldService;


    @ApiOperation("测试接口1")
    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> test(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(new BaseResponse<String>(true, MessageSourceUtil.getMessage("0000"),
                hellWorldService.sayHello(name)));
    }

    @NeedIntercept
    @ApiOperation("测试接口2")
    @RequestMapping(value = "/test2/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> test2(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("dd");
    }

    @ApiOperation("5分钟只能调用一次接口测试")
    @RequestMapping(value = "/limittest/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> limitTest(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(hellWorldService.limitSayHello(name));
    }

    @ApiOperation("异常接口测试")
    @RequestMapping(value = "/test/error", method = RequestMethod.GET)
    public void testerror() {
        hellWorldService.testerror();
    }


}
