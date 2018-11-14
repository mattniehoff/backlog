package com.mattniehoff.backlog.utils;

import com.mattniehoff.backlog.model.igdb.IgdbImageSize;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IgdbImageUtilsTest {

    @Test
    public void shouldGenerateThumbImageUrl() {
        String expectedResult = "http://images.igdb.com/igdb/image/upload/t_thumb/kpnxx0mtm8iv1041h67t.jpg";
        String calculatedResult = IgdbImageUtils.generateImageUrl("kpnxx0mtm8iv1041h67t", IgdbImageSize.thumb);
        Assert.assertEquals(expectedResult, calculatedResult);
    }

    @Test
    public void shouldGenerateEmptyStringOnEmptyHash() {
        String expectedResult = "";
        String calculatedResult = IgdbImageUtils.generateImageUrl("", IgdbImageSize.thumb);
        Assert.assertEquals(expectedResult, calculatedResult);
    }

    @Test
    public void shouldGenerateEmptyStringOnNullHash() {
        String expectedResult = "";
        String calculatedResult = IgdbImageUtils.generateImageUrl(null, IgdbImageSize.thumb);
        Assert.assertEquals(expectedResult, calculatedResult);
    }
}