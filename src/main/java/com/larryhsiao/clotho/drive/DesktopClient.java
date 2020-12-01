package com.larryhsiao.clotho.drive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.larryhsiao.clotho.Source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Source to build a {@link Drive} instance for desktop client.
 */
public class DesktopClient implements Source<Drive> {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final String appName;
    private final InputStream credentialStream;
    private final String storagePath;
    private final String[] scopes;

    public DesktopClient(
        String appName,
        InputStream credentialStream,
        String storagePath,
        String... scopes
    ) {
        this.appName = appName;
        this.credentialStream = credentialStream;
        this.storagePath = storagePath;
        this.scopes = scopes;
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        return new AuthorizationCodeInstalledApp(
            new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                JSON_FACTORY,
                GoogleClientSecrets.load(
                    JSON_FACTORY,
                    new InputStreamReader(credentialStream)
                ),
                Arrays.asList(scopes)
            ).setDataStoreFactory(new FileDataStoreFactory(new File(storagePath)))
                .setAccessType("offline")
                .build(),
            new LocalServerReceiver.Builder().setPort(8888).build()
        ).authorize("user");
    }

    @Override
    public Drive value() {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            return new Drive.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                getCredentials(HTTP_TRANSPORT)
            ).setApplicationName(appName)
                .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
