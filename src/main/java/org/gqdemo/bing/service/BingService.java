package org.gqdemo.bing.service;

import org.gqdemo.bing.domian.Bing;

import java.io.IOException;
import java.util.List;

public interface BingService {

    List<Bing> getBing(String url) throws IOException;
}
