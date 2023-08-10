package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductReqDto;
import com.lawencon.ticketjosep.dto.subproduct.SubscribedProductResDto;

public interface SubscribedProductService {
	InsertResDto insert(SubscribedProductReqDto data);

	List<SubscribedProductResDto> getAll(Long userId);
}
