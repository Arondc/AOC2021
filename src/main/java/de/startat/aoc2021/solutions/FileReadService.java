package de.startat.aoc2021.solutions;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileReadService {

    public List<String> getFileLines(String filename) throws URISyntaxException, IOException {
        URL url = getClass().getClassLoader().getResource(filename);
        File f = new File(url.toURI());
        return Files.readAllLines(f.toPath());
    }

    public List<Integer> getFileIntegers(String filename) throws URISyntaxException, IOException {
        return getFileLines(filename).stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
