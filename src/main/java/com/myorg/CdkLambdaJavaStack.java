package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;


public class CdkLambdaJavaStack extends Stack {
    public CdkLambdaJavaStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkLambdaJavaStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // Defines a new lambda resource
        final Function hello = Function.Builder.create(this, "HelloHandler")
        .runtime(Runtime.NODEJS_14_X)         // execution environment
        .code(Code.fromAsset("lambda")) // code loaded from the "lambda" directory
        .handler("hello.handler")    // file is "hello", function is "handler"
        .build();

        // Defines our hitcounter resource
        final HitCounter helloWithCounter = new HitCounter(this, "HelloHitCounter", HitCounterProps.builder()
            .downstream(hello)
            .build());

        // Defines an API Gateway REST API resource backed by our "hello" function
        LambdaRestApi.Builder.create(this, "Endpoint")
            .handler(helloWithCounter.getHandler())
            .build();
    }
}
