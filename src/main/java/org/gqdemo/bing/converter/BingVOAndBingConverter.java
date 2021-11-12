package org.gqdemo.bing.converter;

import org.gqdemo.bing.domian.Bing;
import org.gqdemo.bing.domian.vo.BingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @version v1.0
 * @ClassName BingVO2BingConverter
 * @Description TODO
 * @Author Q
 */
public class BingVOAndBingConverter {
    public static Bing bingVOToBingCoverter(BingVO bingVO) {
        if (!ObjectUtils.isEmpty(bingVO)) {
            Bing bing = new Bing();
            BeanUtils.copyProperties(bingVO, bing);
            Instant start = bingVO.getStartdate().toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            bing.setStartdate(start.atZone(zoneId).toLocalDate());
            Instant end = bingVO.getEnddate().toInstant();
            bing.setEnddate(end.atZone(zoneId).toLocalDate());
            return bing;
        }
        return null;

    }
}
