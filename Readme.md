Clotho Drive
----

Google Drive Web API wrapped by Clotho.

Useage
---

#### Android
```groovy
dependencies {
    implementation ('com.larryhsiao:clotho-drive:{version}') {
        exclude group: 'org.apache.httpcomponents'
    }
    implementation('com.google.api-client:google-api-client-android:1.26.0') {
        exclude group: 'org.apache.httpcomponents'
    }
}
```

#### Desktop
```groovy
dependencies {
    implementation 'com.larryhsiao:clotho-drive:{version}'
    implementation 'com.google.oauth-client:google-oauth-client-jetty:1.23.0'
}
```

Run the unit-tests(Integration tests)
----
To run the tests you have to provide `src/test/resources/credential.json` for Drive API. (Which is the same if the application using this library want to do oauth with `DesktopDrive`.)