## java-benchmark

Runtime benchmarking of comparable java techniques.

#### StreamBenchmark

Compares compound inline λ vs method references for two filter
expressions.

Here is a look at average run-time.

machine specs: 4 core, 8 threads, 3.40 Ghz, 16 Gb RAM

```
Benchmark                                      Mode       Cnt Score         Error       Units
StreamBenchmark.filterCompoundExpressionSingle avgt       4       41.470 ±      7.765   ns/op
StreamBenchmark.filterStreamOperationsSingle   avgt       4       56.697 ±      6.182   ns/op
StreamBenchmark.filterStreamOperationsSmall    avgt       4     4047.431 ±    300.693   ns/op
StreamBenchmark.filterCompoundExpressionSmall  avgt       4     2578.108 ±    596.958   ns/op
StreamBenchmark.filterCompoundExpressionMedium avgt       4    36177.037 ±   1099.521   ns/op
StreamBenchmark.filterStreamOperationsMedium   avgt       4    61845.679 ±   6605.968   ns/op
StreamBenchmark.filterCompoundExpressionLarge  avgt       4   467842.741 ±  54114.738   ns/op
StreamBenchmark.filterStreamOperationsLarge    avgt       4   666865.390 ±  79848.950   ns/op
```

Compound λ expressions appear to be about 1.5 times faster than
multiple method references.
