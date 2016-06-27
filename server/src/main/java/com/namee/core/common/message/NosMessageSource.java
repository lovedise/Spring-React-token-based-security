package com.namee.core.common.message;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * desc : 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 ReloadableResourceBundleMessageSource 클래스의 구현체
 * @class		NosMessageSource.java
 * @author	zzh
 * @date		2014. 10. 17. 2014
 */
public class NosMessageSource extends ReloadableResourceBundleMessageSource implements MessageSource {
	
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	/**
	 * getReloadableResourceBundleMessageSource() 
	 * @param reloadableResourceBundleMessageSource - resource MessageSource
	 * @return ReloadableResourceBundleMessageSource
	 */	
	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}
	/**
	 * getReloadableResourceBundleMessageSource() 
	 * @return ReloadableResourceBundleMessageSource
	 */	
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

    public Properties getProperties(Locale locale) {
        return this.getMergedProperties(locale).getProperties();
    }
	/**
	 * 정의된 메세지 조회
	 * @param code - 메세지 코드
	 * @return String
	 */	
	public String getMessage(String code) {
		return super.getMessage(code, null, Locale.getDefault());
	}
	public String getMessage(String code, Object[] args) {
		return super.getMessage(code, args, Locale.getDefault());
	}

}