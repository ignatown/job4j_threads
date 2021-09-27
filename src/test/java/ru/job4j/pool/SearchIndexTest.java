package ru.job4j.pool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchIndexTest {

    private Integer[] array(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }

    @Test
    public void searchLengthIs6() {
        SearchIndex<Integer> ps = new SearchIndex<>();
        int r = SearchIndex.search(array(6), 5);
        assertThat(r, is(5));
    }

    @Test
    public void searchLengthIs15() {
        SearchIndex<Integer> ps = new SearchIndex<>();
        int r = SearchIndex.search(array(15), 11);
        assertThat(r, is(11));
    }

}