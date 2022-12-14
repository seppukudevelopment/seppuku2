# ecs-codegen

```
Benchmark                                                      Mode  Cnt           Score           Error  Units
CodegenBranchEntityBenchmark.hasComponentByClassBenchmark     thrpt    5  2236584623.802 ±  79381681.183  ops/s
ManualBranchEntityBenchmark.hasComponentByClassBenchmark      thrpt    5  2258844073.771 ±  50020756.840  ops/s

CodegenBranchEntityBenchmark.hasComponentByGenericBenchmark   thrpt    5  2255286707.588 ±  48210329.799  ops/s
ManualBranchEntityBenchmark.hasComponentByGenericBenchmark    thrpt    5  2252456758.571 ±  47468492.509  ops/s

CodegenBranchEntityBenchmark.hasComponentByKClassBenchmark    thrpt    5   103990394.761 ±   1313318.048  ops/s
ManualBranchEntityBenchmark.hasComponentByKClassBenchmark     thrpt    5   233336513.488 ±  14288398.643  ops/s
```

```
Benchmark                                                      Mode  Cnt           Score           Error  Units
CodegenBranchEntityBenchmark.findComponentByClassBenchmark    thrpt    5  1786762994.811 ±  36995644.915  ops/s
ManualBranchEntityBenchmark.findComponentByClassBenchmark     thrpt    5  1789609867.335 ±  42891965.528  ops/s

CodegenBranchEntityBenchmark.findComponentByGenericBenchmark  thrpt    5  1802037863.516 ±  52215585.064  ops/s
ManualBranchEntityBenchmark.findComponentByGenericBenchmark   thrpt    5  1780149997.833 ± 119278510.588  ops/s

CodegenBranchEntityBenchmark.findComponentByKClassBenchmark   thrpt    5   100579623.669 ±   4353982.604  ops/s
ManualBranchEntityBenchmark.findComponentByKClassBenchmark    thrpt    5   211707998.876 ±   3051425.017  ops/s
```