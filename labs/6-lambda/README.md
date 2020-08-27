# 6. Lambda & API Gateway
This lab will be in Javascript because it is like a million times more clear.

Deploy the CloudFormation stack. This will create a rest api using
[API Gateway](https://aws.amazon.com/api-gateway/) and [Lambda](https://aws.amazon.com/lambda/).
The lambda will created with a 'hello world'-like application.

## Lambda
Modify the code in index.js to count either the albums or images based on the path in the request.
The path can be found in the event object, which looks something like this

```json
{
    "resource": "/count/albums",
    "path": "/count/albums",
    "httpMethod": "GET",
    "headers": null,
    "multiValueHeaders": null,
    "queryStringParameters": null,
    "multiValueQueryStringParameters": null,
    "pathParameters": null,
    "stageVariables": null,
    "requestContext": {
        "resourceId": "mt3fiy",
        "resourcePath": "/count/albums",
        "httpMethod": "GET",
        "extendedRequestId": "R7U8qEKSjoEFZAQ=",
        "requestTime": "27/Aug/2020:11:39:48 +0000",
        "path": "/count/albums",
        "accountId": "040506089782",
        "protocol": "HTTP/1.1",
        "stage": "test-invoke-stage",
        "domainPrefix": "testPrefix",
        "requestTimeEpoch": 1598528388117,
        "requestId": "673ea1c2-5cd6-4031-b450-3bc645efa394",
        "identity": {
            "cognitoIdentityPoolId": null,
            "cognitoIdentityId": null,
            "apiKey": "test-invoke-api-key",
            "principalOrgId": null,
            "cognitoAuthenticationType": null,
            "userArn": "arn:aws:sts::040506089782:assumed-role/AWSReservedSSO_AdministratorAccess_8219a806ceb01083/Rob.Kenis@axxes.com",
            "apiKeyId": "test-invoke-api-key-id",
            "userAgent": "aws-internal/3 aws-sdk-java/1.11.829 Linux/4.9.217-0.1.ac.205.84.332.metal1.x86_64 OpenJDK_64-Bit_Server_VM/25.262-b10 java/1.8.0_262 vendor/Oracle_Corporation",
            "accountId": "040506089782",
            "caller": "AROAQS3SZ4E3B7J7JA3M3:Rob.Kenis@axxes.com",
            "sourceIp": "test-invoke-source-ip",
            "accessKey": "ASIAQS3SZ4E3NEJVOYHX",
            "cognitoAuthenticationProvider": null,
            "user": "AROAQS3SZ4E3B7J7JA3M3:Rob.Kenis@axxes.com"
        },
        "domainName": "testPrefix.testDomainName",
        "apiId": "gr94k21kc6"
    },
    "body": null,
    "isBase64Encoded": false
}
```

Documentation about the aws-sdk for nodejs can be found [here](https://docs.aws.amazon.com/AWSJavaScriptSDK/latest/AWS/DynamoDB.html)
Counting the objects in a table is a property of the [Scan](https://docs.aws.amazon.com/AWSJavaScriptSDK/latest/AWS/DynamoDB.html#scan-property) operation.

You can update your code by copy-pasting it in the lambda console. Paste it in the window for function code and click save in the top right corner.

![Lambda Code](../../assets/lambda_code.png)

## API Gateway
There's not much to it actually. Have a look at your API Gateway, but once
it is set up, you don't have to do anything.
