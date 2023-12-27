package com.zl.community.mapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.zl.community.model.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @Author : ZL
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-12-07 10:10:19
* @Entity com.zl.community.model.entity.User
*/
public interface UserMapper extends BaseMapper<UserEntity> {
    //java.util.Optional 是 Java 8 中引入的一个类，用于表示一个值可能存在或不存在的情况。在 Java 之前，通常使用 null 值来表示一个值不存在，但是使用 null 值会带来一些问题，如空指针异常等。
    //
    //Optional 类通过提供一组 API 来解决这个问题，可以让我们更加优雅地处理可能为空的值。它有以下几个主要的方法：
    //
    //of(T value)：创建一个非空的 Optional 对象，如果传入的值为 null，则会抛出 NullPointerException 异常。
    //ofNullable(T value)：创建一个 Optional 对象，如果传入的值为 null，则返回一个空的 Optional 对象。
    //isEmpty()：判断 Optional 对象是否为空。
    //get()：获取 Optional 对象中的值，如果对象为空，则会抛出 NoSuchElementException 异常。
    //orElse(T other)：获取 Optional 对象中的值，如果对象为空，则会返回传入的默认值。
    //orElseGet(Supplier<? extends T> other)：获取 Optional 对象中的值，如果对象为空，则会执行传入的 Supplier 并返回其结果。
    //orElseThrow(Supplier<? extends X> exceptionSupplier)：获取 Optional 对象中的值，如果对象为空，则会抛出传入的异常。
    //ifPresent(Consumer<? super T> consumer)：如果 Optional 对象不为空，则执行传入的 Consumer。
    //通过使用 Optional 类，我们可以更好地处理可能为空的值，并且可以避免出现空指针异常等问题。
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param userName 用户名
     * @param email    邮箱
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<UserEntity> selectByUserNameOrEmailOrPhone(@Param("userName") String userName,
                                                        @Param("email") String email,
                                                        @Param("phone") String phone);


}




