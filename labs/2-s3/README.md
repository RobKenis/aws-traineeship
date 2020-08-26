# 2. S3

## Setting up an S3 bucket
An S3 bucket has been added to the CloudFormation template. **Update** your current stack to create the extra resources.
This will also update the IAM role of the Beanstalk Environment to **allow** actions to S3.
> If you don't specify a name for your S3 bucket, CloudFormation will create it for you. The advantage of this is that you can
> replace buckets using CloudFormation without having to worry about naming conflicts.

## Uploading an image to S3
Implement `public Optional<Image> upload(String filename, InputStream inputStream, long size)` in `com.axxes.traineeship.photoalbum.image.service.S3StorageService`. 
The solution can be found in `com.axxes.traineeship.photoalbum.image.service.solution.S3StorageService`.

What steps should I take:
1. Use [s3:PutObject](https://docs.aws.amazon.com/AmazonS3/latest/dev/UploadObjSingleOpJava.html) to store the file on S3. This method can take a File or an InputStream. 
When using an InputStream, the size of the object must be set in **ObjectMetadata** so S3 knows what to expect.
2. Get the URL to the uploaded object. (I couldn't find the documentation, but [this](https://docs.aws.amazon.com/AmazonS3/latest/dev/UploadObjSingleOpJava.html) should help)

## Dealing with permissions
Setting the AWS Credentials as environment variables, then running the application using `java -jar target/photo-album-0.0.1-SNAPSHOT.jar`
worked best for me. A big plus for this is that you don't have to do code changes to make it work on Beanstalk.

### What to do on access denied ?
`com.amazonaws.services.s3.model.AmazonS3Exception: Access Denied (Service: Amazon S3; Status Code: 403; Error Code: AccessDenied; ...)`

When running locally, make sure your AWS CLI credentials are set in the current session. Your user has all the permissions 
to upload files to S3, so that should work. 
When running on Beanstalk, make sure the attached EC2 role has permissions to upload to S3.

## Help, I can't view my image using the URL
That's right, because the image is not public. This means that only the account owner can view the image.
To resolve this, set the `ACL` to `PublicRead` when uploading the image to S3.
> Setting objects to public makes it available to everyone to read. When working on a project, talk this through with your team :) 
