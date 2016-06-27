package com.namee.api.service;

import com.namee.api.dto.SSODto;
import com.namee.api.model.SSO;
import com.namee.core.common.util.ISO8601Utils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by namee on 2016. 6. 14..
 */
@Service
public class ObjectMapper extends ModelMapper {
    @PostConstruct
    public void init(){
        this.createTypeMap(Date.class, String.class).setConverter(new Converter<Date, String>() {
            public String convert(MappingContext<Date, String> context) {
                return ISO8601Utils.format(context.getSource(), true);
            }
        });
        getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public SSODto toSSoDto(SSO sso) {
        return this.map(sso, SSODto.class);
    }
}
