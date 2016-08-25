package com.doppler.blog.Service;

import com.doppler.blog.mappers.SettingMapper;
import com.doppler.blog.models.Setting;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.doppler.blog.GlobalConstants.UPDATE_SETTINGS;
import static com.doppler.blog.GlobalConstants.UPDATE_SETTINGS_FAIL;

@Service
public class SettingService {
    Logger logger = LoggerFactory.getLogger(SettingService.class);
    @Resource
    private SettingMapper settingMapper;

    public Setting getSetting(){
        return settingMapper.getSettings();
    }

    public Setting updateSetting(Setting setting) {
        int count = settingMapper.updateSettings(setting);
        Preconditions.checkState(count == 1,UPDATE_SETTINGS_FAIL.val());
        logger.info(UPDATE_SETTINGS.val());
        return setting;
    }

}
