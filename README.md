[![](https://jitpack.io/v/Omega-R/OmegaGZipInterceptor.svg)](https://jitpack.io/#Omega-R/OmegaGZipInterceptor)

# OmegaGZipInterceptor
helper unziping files from server


# Installation
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    compile 'com.github.Omega-R:OmegaGZipInterceptor:v1.0'
}
```

# Usage
Example of usage
```
OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new AcceptGzipInterceptor())
                .addInterceptor(new UngzippingInterceptor())
                .build();
```
