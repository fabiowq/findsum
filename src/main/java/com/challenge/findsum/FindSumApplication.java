package com.challenge.findsum;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class FindSumApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindSumApplication.class, args);
	}

	@Bean
    FindSum findSum() {
	    return new FindSumFwqImpl();
    }

    @Bean
    CommandLineRunner runner(
            @Value("classpath:input.txt") Resource inputResource,
            FindSum findSum) {
        return args -> {
            log.info("Using implementation {}", findSum.getClass().getName());
            try (Stream<String> lines = Files.lines(Paths.get(inputResource.getURI()))) {
                List<PrintLine> printLines = new ArrayList<>();
                lines.forEach(line -> {
                    if ((StringUtils.isNotBlank(line)) && (!line.contains("Int Array"))) {
                        String[] tokens = line.replaceAll("[\\t\\s]+", " ").split("[\\t\\s]");
                        int[] intArray = null;
                        if (tokens.length >= 1) {
                            String intsString = tokens[0];
                            if (StringUtils.isNotBlank(intsString)) {
                                intArray = Arrays.stream(intsString.trim().replaceAll("\\[|\\]", "").split(","))
                                        .mapToInt(s -> Integer.parseInt(s.trim())).toArray();
                            }
                        }
                        Integer num = null;
                        if (tokens.length >= 2) {
                            String numString = tokens[1];
                            if (StringUtils.isNotBlank(numString) && NumberUtils.isDigits(numString.trim())) {
                                num = Integer.parseInt(numString.trim());
                            }
                        }
                        Boolean expectedResult = null;
                        if (tokens.length >= 3) {
                            String expectedResultString = tokens[2];
                            if (StringUtils.isNotBlank(expectedResultString)) {
                                expectedResult = Boolean.valueOf(expectedResultString.trim());
                            }
                        }
                        Boolean resultBool = null;
                        if (intArray != null && num != null && expectedResult != null) {
                            resultBool = findSum.find(intArray, num);
                        } else {

                        }
                        printLines.add(PrintLine.builder()
                                .line(line)
                                .expectedResult(Optional.ofNullable(expectedResult))
                                .actualResult(Optional.ofNullable(resultBool)).build()
                        );
                    }
                });
                log.info("Int Array\tSum Number\tExpected Result\t\tActual Result");
                printLines.stream().forEach(p ->
                        log.info("{}\t\t\t{}", p.getLine(), p.getActualResult()
                                .map(b -> ((p.getExpectedResult().isPresent()) && (!p.getExpectedResult().get().equals(b)))?
                                        b.toString() + " *": b.toString()

                                )
                                .orElse("ignored")
                        )
                );
            }
        };
    }


    @Builder
    @Getter
    private static class PrintLine {
	    private String line;
        private Optional<Boolean> expectedResult;
        private Optional<Boolean> actualResult;
    }

}
