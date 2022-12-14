# ecs-codegen

```
Benchmark                                                      Mode  Cnt           Score          Error  Units
CodegenBranchEntityBenchmark.hasComponentByClassBenchmark     thrpt    5  2271795892.976 ±  1112896.669  ops/s
ManualBranchEntityBenchmark.hasComponentByClassBenchmark      thrpt    5  2191604380.763 ± 90252781.430  ops/s

CodegenBranchEntityBenchmark.hasComponentByGenericBenchmark   thrpt    5  2233613837.282 ±  4743061.134  ops/s
ManualBranchEntityBenchmark.hasComponentByGenericBenchmark    thrpt    5  2258245406.037 ± 30836263.542  ops/s

CodegenBranchEntityBenchmark.hasComponentByKClassBenchmark    thrpt    5  2266298108.723 ± 47732834.860  ops/s
ManualBranchEntityBenchmark.hasComponentByKClassBenchmark     thrpt    5  2231965314.583 ± 32705510.213  ops/s

CodegenBranchEntityBenchmark.findComponentByClassBenchmark    thrpt    5  1748423410.629 ± 69306745.115  ops/s
ManualBranchEntityBenchmark.findComponentByClassBenchmark     thrpt    5  1748620643.386 ± 60843406.443  ops/s

CodegenBranchEntityBenchmark.findComponentByGenericBenchmark  thrpt    5  1806841914.107 ± 23888697.191  ops/s
ManualBranchEntityBenchmark.findComponentByGenericBenchmark   thrpt    5  1794695205.402 ±  8311181.822  ops/s

CodegenBranchEntityBenchmark.findComponentByKClassBenchmark   thrpt    5  1782583290.287 ± 47234128.639  ops/s
ManualBranchEntityBenchmark.findComponentByKClassBenchmark    thrpt    5  1795660487.853 ± 27066035.071  ops/s
```
