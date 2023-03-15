package com.hlfront.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlfront.entity.TFrontUser;
import com.hlfront.result.R;
import com.hlfront.result.Result;
import com.hlfront.service.FrontUserService;
import com.hlfront.utils.SendSMS;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author 贾佳
 * @date 2023/3/7 10:23
 */
@RestController
@RequestMapping("/sms")
@Slf4j
@Api("用户登录")
public class RegisterController {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    FrontUserService frontUserService;

    private final Logger logger= LoggerFactory.getLogger(RegisterController.class);

    @PostMapping("/sendCode")
    public Result<String> sendCode(@RequestParam("phoneNumber")String phoneNumber){
        int code = SendSMS.sendCode(phoneNumber);
        if (code != 0){
            redisTemplate.opsForValue().set(phoneNumber,code,30, TimeUnit.SECONDS);
            return R.success("请求成功");
        }
        return R.fail("请求失败");
    }

    @PostMapping("/PhoneLogin")
    public Result<String> phoneLogin(@NotNull @RequestParam("phoneNumber") String PhoneNumber, @NotNull @RequestParam("Code")int code){
        TFrontUser tFrontUser = null;
        Authentication authenticate = null;
        Object msg = redisTemplate.opsForValue().get(PhoneNumber);
        if (msg != null){
            tFrontUser.setPhoneNumber(PhoneNumber);
            QueryWrapper<TFrontUser> wrapper = new QueryWrapper<TFrontUser>();
            if (wrapper.eq("phone_number",PhoneNumber)!= null){
//                authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(PhoneNumber))
            }
            else if (code == (int)redisTemplate.opsForValue().get(PhoneNumber)){
                frontUserService.save(tFrontUser);
                return R.success("登陆成功");
            }
        }
        return R.fail("登录失败");
    }
}
