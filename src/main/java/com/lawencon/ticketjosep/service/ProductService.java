package com.lawencon.ticketjosep.service;

import java.util.List;

import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.product.ProductInsertReqDto;
import com.lawencon.ticketjosep.dto.product.ProductUpdateReqDto;

public interface ProductService {
	InsertResDto insert(ProductInsertReqDto data);
	List<ProductUpdateReqDto> getAll();
	UpdateResDto update(ProductUpdateReqDto data);
}
