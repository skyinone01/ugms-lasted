package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.Question;
import com.ug369.backend.service.entity.mysql.QuestionItem;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roy on 2017/01/09.
 */
@Repository
public interface QuestionItemRepository extends RDBRepository<QuestionItem, Long> {


}
