package com.softrear.libraryapi.model;

import lombok.Data;

@Data
public class CommonResponse {
    private Boolean hasError;
    private String decentMessage;
    private String errorDetails;
    private String content;




//    CommonResponse({this.hasError,this.decentMessage,this.errorMessage,})
}
