package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Egor Topor"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIllegalException() {
        String path = "./data/pair_wrong.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("surname"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIllegalException2() {
        String path = "./data/pair_wrong2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("surname"));
    }
}