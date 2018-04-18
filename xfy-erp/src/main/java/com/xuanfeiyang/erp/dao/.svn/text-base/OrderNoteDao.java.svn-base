package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.OrderNote;

public interface OrderNoteDao {
	void delete(Integer id);

    Long insert(OrderNote record);
    
    void update(OrderNote record);

    OrderNote load(Integer id);
    
    List<OrderNote> queryByOrder(@Param("orderId")Integer orderId);
}
