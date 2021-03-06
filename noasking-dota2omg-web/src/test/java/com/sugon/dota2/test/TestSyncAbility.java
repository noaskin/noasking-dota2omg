package com.sugon.dota2.test;

import com.noasking.dota2.WebApplication;
import com.noasking.dota2.web.WebConst;
import com.noasking.dota2.web.service.AbilityParseService;
import com.noasking.dota2.web.utils.URLImageDownload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by MaJing on 2018/1/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestSyncAbility {

    @Autowired
    private AbilityParseService abilityParseService;

    @Test
    public void testCreateAbility() throws IOException {
//        String[] aa = FileUtils.readFileToString(new File("D:\\TEMP\\aaa\\npc_abilities.txt")).split("");
        String aaa = FileUtils.readFileToString(new File("D:\\TEMP\\aaa\\npc_abilities.txt"));

        abilityParseService.execute(aaa);
    }

    @Test
    public void testDownloadAbilityImages() throws IOException {
//        URLImageDownload.download("http://cdn.dota2.com/apps/dota2/images/abilities/centaur_hoof_stomp_lg.png", WebConst.ImagePath.ABILITY + "11.png");
        System.out.println(abilityParseService.downloadAbilityImages(null));
    }

}
