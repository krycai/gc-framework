package com.allen.boot.inject;

import com.allen.boot.po.User;

/**
 * Created by xuguocai on 2021/3/26 16:03
 */
public interface SpringDao {

    public void ok();

    public void save(User user);

    void saveFactory();

    void insertFactory();

}
