package com.pyip.rpc.api;

import com.pyip.rpc.pojo.User;

public interface IUserService {
    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    User getById(int id) throws Exception;
}
