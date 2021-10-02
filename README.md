# retrofit-converter-jsoup

[Retrofit](https://square.github.io/retrofit/) converter which uses
[jsoup](https://jsoup.org/) for serialization from HTML.

## Usage

Add the converter when configuring your Retrofit instance

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://github.com/")
    .addConverterFactory(JsoupConverterFactory())
    .build()
```

then services created from that Retrofit instance will support returning jsoup
`Document`s.

```kotlin
interface WebPageService {

    @GET("rsookram/retrofit-converter-jsoup/blob/main/README.md")
    suspend fun fetch()
}
```

## Download

Add jitpack to your root `build.gradle` at the end of `repositories`:

```gradle
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then add the dependency to your module-level `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.rsookram:retrofit-converter-jsoup:0.0.1'
}
```

## Building

retrofit-converter-jsoup can be built from source by cloning this repository
and using Gradle.

```
git clone https://github.com/rsookram/retrofit-converter-jsoup.git
cd retrofit-converter-jsoup
./gradlew build
```

The built JAR can be found in `retrofit-converter-jsoup/build/libs/`.

## License

```
Copyright 2021 Rashad Sookram

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
