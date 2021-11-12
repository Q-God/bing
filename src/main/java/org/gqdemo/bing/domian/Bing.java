package org.gqdemo.bing.domian;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_bing")
public class Bing {

    @Id
    private Long id;

    private LocalDate startdate;

    private String fullstartdate;

    private LocalDate enddate;

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
