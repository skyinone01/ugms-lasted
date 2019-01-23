package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Item;
import com.ug369.backend.service.entity.mysql.Question;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 2017/01/09.
 */
@Repository
public interface ItemRepository extends RDBRepository<Item, Long> {


}
