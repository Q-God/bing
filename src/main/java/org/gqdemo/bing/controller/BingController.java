package org.gqdemo.bing.controller;

import org.gqdemo.bing.common.ResponseInfo;
import org.gqdemo.bing.common.ResultData;
import org.gqdemo.bing.service.BingHandler;
import org.gqdemo.bing.service.BingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public abstract class BingController {

    @Autowired
    private BingHandler bingHandler;
}
