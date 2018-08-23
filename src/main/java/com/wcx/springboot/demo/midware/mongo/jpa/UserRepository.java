package com.wcx.springboot.demo.midware.mongo.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    /**
     *  根据id查询
      * @param id
     * @param pageable
     * @return
     */
    Page<User> findById(long id, Pageable pageable);

    /**
     * and查询
     * @param id
     * @param status
     * @return
     */
    List<User> findByIdAndStatus(long id, int status);


    /**
     * and分页查询
     * @param id
     * @param status
     * @param pageable
     * @return
     */
    Page<User> findByIdAndStatus(String id, int status, Pageable pageable);


    /**
     * order by排序
     * @param id
     * @return
     */
    List<User> findByIdOrderByStatusDesc(String id);

    /**
     * in分页查询
     * @param id
     * @param status
     * @param pageable
     * @return
     */
    Page<User> findByIdAndStatusIn(String id, Collection<Integer> status, Pageable pageable);

    /**
     * in获取count
     * @param id
     * @param status
     * @return
     */
    int countByIdAndStatusIn(String id, Collection<Integer> status);
}
