package org.gqdemo.bing.domian.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BingVO {

    private Long id;

    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date startdate;

    private String fullstartdate;

    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date enddate;

    private String url;

    private String urlbase;

    private String copyright;

    private String copyrightlink;

    private String title;

    private String quiz;

    private String wp;

    private String hsh;

    private String drk;

    private String top;

    private String bot;

    @Transient
    private List<String> hs;
}
