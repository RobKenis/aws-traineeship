# 7. Bonus
Here comes the cool stuff.

We're going to add tags to images about what's in the image.

## Rekognition
[AWS Rekognition](https://aws.amazon.com/rekognition/) is a fully managed service to do some machine learning and stuff 
on images and video. You can try the demo in the [console](https://eu-west-1.console.aws.amazon.com/rekognition/home?region=eu-west-1#/)

## What do I have to do ?
- Create a lambda that is triggered from SQS
- Send a message to SNS/SQS when a new image is uploaded
- In the lambda, use the Rekognition API to extract the tags. This process is async, so the response of the operation is
sent to SNS.
- Create a new SNS topic that receives Rekognition responses
- Subscribe something to the topic. This can be another lambda, or an endpoint of your Java application.
- Handle the response and add the labels to the image in dynamodb
