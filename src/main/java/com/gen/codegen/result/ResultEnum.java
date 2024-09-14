 package com.gen.codegen.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

 /**
  * @author haoxiaoming
  */
 @Getter
 @AllArgsConstructor
 public enum ResultEnum {

     // 成功
     OK(0, "ok"),
     // 失败
     FAIL(-1, "fail");

     private final Integer code;
     private final String msg;

 }
