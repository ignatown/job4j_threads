package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenAdd() {
        Cache testCache = new Cache();
        testCache.add(new Base(1, 0));
        assertThat(testCache.get(1), is(new Base(1, 0)));
    }

    @Test
    public void whenUpdate() {
        Base newBase = new Base(1, 0);
        newBase.setName("Test");
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        cache.update(newBase);
        Base testBase = new Base(1, 1);
        testBase.setName("Test");
        assertThat(cache.get(1), is(testBase));
    }

    @Test
    public void whenDelete() {
        Base testBase = new Base(1, 0);
        Cache testCache = new Cache();
        testCache.add(testBase);
        testCache.delete(testBase);
        assertNull(testCache.get(testBase.getId()));
    }
}