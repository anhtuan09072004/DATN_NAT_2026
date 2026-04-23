package com.example.Backend_2026.infrastructure.common;

import com.example.Backend_2026.infrastructure.constant.PhanTrangConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageableRequest {
    private int page = PhanTrangConstant.DEFAULT_PAGE;
    private int sizePage = PhanTrangConstant.DEFAULT_SIZE;
}