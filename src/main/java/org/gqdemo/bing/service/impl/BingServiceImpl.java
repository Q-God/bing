package org.gqdemo.bing.service.impl;

import org.gqdemo.bing.domian.Bing;
import org.gqdemo.bing.service.BingService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BingServiceImpl implements BingService {
    @Override
    public List<Bing> getBing(String url) throws IOException {
        return null;
    }

//    @Autowired
//    public List<Bing> getBing(String url) throws IOException {
//        return null;
//    }

//    // 定义jackson对象
//    private static final ObjectMapper om = new ObjectMapper();
//    @Autowired
//    private BingRepository bingRepository;
//
//    @Override
//    public List<Bing> getBing(String url) throws IOException {
//        String json = JsoupUtils.Crawler(url);
//        JsonNode node = om.readTree(json);
//        JsonNode images = node.get("images");
//        List<Bing> bingList = om.readValue(images.toString(), new TypeReference<List<Bing>>() {
//        });
//        bingList.stream().forEach(bing -> {
//            if (bingRepository.findBingByStartDate(bing.getStartdate()) == null)
//            {
//                bingRepository.save(bing);
//            }
//        });
//        return bingList;
//    }
}
