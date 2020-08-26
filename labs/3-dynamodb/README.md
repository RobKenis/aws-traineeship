# 3. DynamoDB

## Setting up a DynamoDB table
You know the drill, the updated CloudFormation is under cloudformation/

## Storing albums in DynamoDB
Implement `save` and `getAll` in `com.axxes.traineeship.photoalbum.album.repository.AlbumRepositoryImpl`.
The solution is in `com.axxes.traineeship.photoalbum.album.repository.solution.AlbumRepositoryImpl`. 

The solution uses the low-level client to handle items, this means that you have to do the mapping to Java objects yourself.
If you want to use the [Dynamodb Mapper](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.html),
feel free, it does some more magic behind the scenes. It's like Spring-data for dynamodb.
