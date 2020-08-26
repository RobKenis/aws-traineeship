# 4. S3 Static Website

## Updating the backend
If we would just deploy the frontend, some things would catch on fire. The frontend is served over **https** because
CloudFront can handle this by default. Beanstalk is served over **http**, we can not change that because we can't request
a certificate for **elasticbeanstalk.com** and we don't have our own zone for DNS.
To resolve this, we can put the backend behind CloudFront to handle SSL termination.

Update your backend stack with the CloudFormation template in cloudformation/.

## Building the frontend
Change `REACT_APP_BACKEND_URL` in `.env` to you **CloudFront** URL. The URL from beanstalk will work locally,
but not on the deployed version (because mixed content).

```bash
$ yarn build
```
> If you want to use npm, go ahead, it shouldn't break anything.

This will create a `build/` directory. This directory can be served as a static website, for example using nginx,
or in our case, S3.

## Deploying the frontend
```bash
$ aws s3 sync build/ s3://${{YOUR_BUCKET}}/ --acl public-read
```
