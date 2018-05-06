package com.xupt.cloud.manager.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public final class Replys {
    private Replys() {}
    public static ResponseEntity success() {
        return new ResponseEntity(HttpStatus.OK);
    }
    public static <T> ResponseEntity success(T data) {
        return new ResponseEntity(data, HttpStatus.OK);
    }

    public static ResponseEntity created(URI location) {
        return ResponseEntity.created(location).build();
    }

    public static ResponseEntity accepted() {
        return ResponseEntity.accepted().build();
    }

    public static ResponseEntity noContent() {
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity unauthorized() {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity unauthorized(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity forbidden() {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity forbidden(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity notFound() {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity notFound(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public static ResponseEntity badRequest(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity notAcceptable() {
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    public static ResponseEntity notAcceptable(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.NOT_ACCEPTABLE);
    }

    public static ResponseEntity gone() {
        return new ResponseEntity(HttpStatus.GONE);
    }

    public static ResponseEntity unprocesableEntity() {
        return ResponseEntity.unprocessableEntity().build();
    }

    public static ResponseEntity unprocesableEntity(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static ResponseEntity error() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity error(final IReplyMsg replyMsg) {
        return new ResponseEntity(replyMsg.toJsonString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
