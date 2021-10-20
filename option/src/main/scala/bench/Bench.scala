package bench

// https://pkolaczk.github.io/overhead-of-optional/

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._

/*
$ sbt "; clean; Jmh/run -i 5 -wi 2 -f 1 -t 1 bench.Bench"

[info] # JMH version: 1.32
[info] # VM version: JDK 17, OpenJDK 64-Bit Server VM, 17+35

[info] Benchmark              Mode  Cnt      Score      Error  Units
[info] Bench.sumSimple        avgt    5   1017.859 ± 1114.616  us/op
[info] Bench.sumOption        avgt    5   3727.473 ±  350.653  us/op
[info] Bench.sumOptionScala   avgt    5  14945.291 ±  325.196  us/op
[info] Bench.sumOptionScala2  avgt    5  10202.406 ± 1267.253  us/op
 */
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
class Bench {
  private[this] final val MAGIC_NUMBER: Long = 7

  // Variant 1. Scala port.
  @inline
  private[this] def getNumber(i: Long): Long = {
    i & 0xff
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def sumSimple(): Long = {
    var sum = 0L
    for (i <- 0 until 1000000) {
      val n = getNumber(i)
      if (n != MAGIC_NUMBER) sum += n
    }
    //assert(sum == 127466507)
    sum
  }

  // Variant 3. Scala port.
  @inline
  private[this] def getOptionalNumber(i: Long): Option[Long] = {
    val n = i & 0xff
    if (n == MAGIC_NUMBER) None else Some(n)
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def sumOption(): Long = {
    var sum = 0L
    for (i <- 0 until 1000000) {
      getOptionalNumber(i).foreach { n => sum += n }
    }
    //assert(sum == 127466507)
    sum
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def sumOptionScala(): Long = {
    val sum = (0 until 1000000).flatMap(getOptionalNumber(_)).sum
    //assert(sum == 127466507)
    sum
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  def sumOptionScala2(): Long = {
    val sum = (0 until 1000000)
      .map(getOptionalNumber(_))
      .foldLeft(0L)((sum, option) =>
        option match {
          case Some(n) => sum + n
          case None    => sum
        }
      )
    //assert(sum == 127466507)
    sum
  }
}
