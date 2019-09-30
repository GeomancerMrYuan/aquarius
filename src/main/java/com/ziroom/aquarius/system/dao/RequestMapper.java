package com.ziroom.aquarius.system.dao;

import com.ziroom.aquarius.system.model.Request;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestMapper {

    int deleteByPrimaryKey(Long requestId);

    int insertSelective(Request record);

    Request selectByPrimaryKey(Long requestId);

    int updateByPrimaryKeySelective(Request record);

    String getReponseByUrl(String url);
}