const AWS = require('aws-sdk')

const IMAGES_TABLE = 'robs-photo-album-images-dev'
const ALBUMS_TABLE = 'robs-photo-album-albums-dev'

const dynamodb = new AWS.DynamoDB({ apiVersion: '2012-08-10' })

const countAlbums = async () => {
  const result = await dynamodb.scan({
    TableName: ALBUMS_TABLE,
    Select: 'COUNT'
  }).promise()
  return result.Count;
}

const countImages = async () => {
  const result = await dynamodb.scan({
    TableName: IMAGES_TABLE,
    Select: 'COUNT'
  }).promise()
  return result.Count;
}

exports.handler = async function(event, context) {
  if (event.path === '/count/albums') {
    return {
      statusCode: 200,
      body: JSON.stringify({ amount: await countAlbums() }),
      isBase64Encoded: false,
    }
  }

  if (event.path === '/count/images') {
    return {
      statusCode: 200,
      body: JSON.stringify({ amount: await countImages() }),
      isBase64Encoded: false,
    }
  }

  return {
    statusCode: 400,
    body: JSON.stringify({ message: 'Path not known' }),
    isBase64Encoded: false,
  }
}
