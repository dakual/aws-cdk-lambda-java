package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;


public class CdkLambdaJavaApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkLambdaJavaStack(app, "CdkLambdaJavaStack", StackProps.builder().build());

        app.synth();
    }
}

