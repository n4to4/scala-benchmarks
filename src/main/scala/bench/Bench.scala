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

/*
$ java -version
openjdk version "1.8.0_252"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_252-b09)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.252-b09, mixed mode)

[info] Benchmark             Mode  Cnt          Score           Error  Units
[info] Bench.privateField   thrpt   20  523793413.323 ±   9236192.770  ops/s
[info] Bench.privateMethod  thrpt   20  507092714.807 ±   7950908.549  ops/s
[info] Bench.privateThis    thrpt   20  464653760.761 ± 104438699.974  ops/s
[success] Total time: 1207 s (20:07), completed Jun 26, 2021 8:56:46 AM

---

$ java -version
openjdk version "11.0.11" 2021-04-20
OpenJDK Runtime Environment AdoptOpenJDK-11.0.11+9 (build 11.0.11+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK-11.0.11+9 (build 11.0.11+9, mixed mode)

[info] Benchmark             Mode  Cnt          Score          Error  Units
[info] Bench.privateField   thrpt   20  456567261.973 ± 11342642.033  ops/s
[info] Bench.privateMethod  thrpt   20  428382297.787 ± 21882136.930  ops/s
[info] Bench.privateThis    thrpt   20  432996497.279 ± 24507795.638  ops/s
[success] Total time: 1207 s (20:07), completed Jun 26, 2021, 9:23:07 AM
 */
