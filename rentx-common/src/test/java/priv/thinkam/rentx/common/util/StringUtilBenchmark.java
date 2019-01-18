package priv.thinkam.rentx.common.util;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * String util benchmark
 * <p>
 *     conclusion: 'StringUtil.format' performs better than 'String.format' and 'MessageFormat.format'
 * </p>
 *
 * @author yanganyu
 * @date 2019/1/18 10:32
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 2, time = 5)
public class StringUtilBenchmark {
	@Benchmark
	public String testFormat() {
		return StringUtil.format("aa{}cc", "bb");
	}

	@Benchmark
	public String testFormat1() {
		return String.format("aa%scc", "bb");
	}

	@Benchmark
	public String testFormat2() {
		return MessageFormat.format("aa{0}cc", "bb");
	}

	public static void main(String[] args) throws RunnerException {
		new Runner(
				new OptionsBuilder()
						.include(StringUtilBenchmark.class.getSimpleName())
						.forks(1)
						.build()
		).run();
	}
}
