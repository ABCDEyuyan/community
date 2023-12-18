package com.example.communityuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.MainApplication;
import com.zl.community.mapper.UserMapper;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.LoginLogEntity;
import com.zl.community.model.entity.UserEntity;
import com.zl.community.model.swagger.LoginLogApi;
import com.zl.community.service.LoginLogService;
import com.zl.community.service.UserService;
import com.zl.community.service.impl.LoginLogServiceImpl;
import com.zl.community.service.security.CustomUserDetailsServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author : ZL
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainApplication.class)
public class TestLoginLogPgTest {
    @Autowired
    private LoginLogServiceImpl loginLogService;
    @Test
    public void testLoginLogPage(){
        PageQueryModel model=new PageQueryModel();
        model.setPageNo(1);
        model.setPageSize(2);
        IPage<LoginLogEntity> iPage = loginLogService.pageQuery(model);
        System.out.println(iPage.getRecords());
        IPage<LoginLogApi> page = new Page<>();
        BeanUtils.copyProperties(iPage, page, "records");
        List<LoginLogApi> newRecords = iPage.getRecords().stream().map(sysLoginLog -> {
            Class<LoginLogApi> sysLoginLogClass = LoginLogApi.class;
            LoginLogApi loginLog = null;
            try {
                loginLog = sysLoginLogClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(sysLoginLog, loginLog);
            return loginLog;
        }).collect(Collectors.toList());
        page.setRecords(newRecords);
        System.out.println("page = " + page);
        System.out.println("page.getTotal() = " + page.getTotal());
    }
    @Autowired
    private CustomUserDetailsServiceImpl userService;
    @Test
    public void testUserPage(){
        UserDetails bababbbbb = userService.loadUserByUsername("user1@example.com");
        System.out.println("bababbbbb = " + bababbbbb);
    }

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectByUser(){
        Optional<UserEntity> userEntity = userMapper.selectByUserNameOrEmailOrPhone("user1@example.com","","");
        System.out.println("userEntity = " + userEntity);
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void testBCryptPassword(){
        String sqlPassword="password1";
        String sqlPassword2="password2";
        String encode = bCryptPasswordEncoder.encode(sqlPassword);
        String encode2 = bCryptPasswordEncoder.encode(sqlPassword2);
        System.out.println("encode = " + encode);
        System.out.println("encode2 = " + encode2);

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user.getPassword());
    }
}
