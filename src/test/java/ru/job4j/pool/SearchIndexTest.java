package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        int r = ps.search(array(6), 5);
        assertThat(r, is(5));
    }

    @Test
    public void searchLengthIs15() {
        SearchIndex<Integer> ps = new SearchIndex<>();
        int r = ps.search(array(15), 11);
        assertThat(r, is(11));
    }

}