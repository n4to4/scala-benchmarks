package bench

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._

/** > sbt "bench/jmh:run -i 10 -wi 10 -f 2 -t 1 bench.Bench"
  */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class Bench {
  @Benchmark
  def privateThis: Int = Foo.viaPrivateThis

  @Benchmark
  def privateField: Int = Foo.viaPrivateField

  @Benchmark
  def privateMethod: Int = Foo.viaPrivateMethod
}

object Foo {
  private[this] val privateThis: Int = 123
  private val privateField: Int = 123
  private def privateMethod: Int = 123

  val viaPrivateThis: Int = privateThis
  val viaPrivateField: Int = privateField
  val viaPrivateMethod: Int = privateMethod
}
