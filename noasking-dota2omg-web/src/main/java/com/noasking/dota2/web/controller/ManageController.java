package com.noasking.dota2.web.controller;

import com.noasking.dota2.web.service.AbilityParseService;
import com.noasking.dota2.web.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Cache;

import java.io.IOException;

/**
 * Created by MaJing on 2018/1/30.
 */
@RestController
@RequestMapping("manage")
public class ManageController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("getHeroTopList")
    public void cleanCache(String cacheName) {
        if (StringUtils.isEmpty(cacheName)) {

        }
    }

    @Autowired
    private AbilityParseService abilityParseService;

    @RequestMapping(value = "getHeroTopList", method = RequestMethod.POST)
    public void reloadAbility(String ability) throws IOException {
        abilityParseService.execute(ability);
    }

}
