Performance issues with kotlin-dsl

The trivial line `assertThat(true).isTrue()` from assertj uses too much CPU (more than 200%)

Steps to reproduce :
1. Uncomment the assertj dependency in build.gradle.kts
2. Go to MyTest.kt
3. Try to add an assertion in the test

Done with Intellij 2019.1 and Gradle 5.3.1
