package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SubController { //요청하는 url마다 내용을 담기위해 생성

	void execute(HttpServletRequest req, HttpServletResponse resp) ;
}
