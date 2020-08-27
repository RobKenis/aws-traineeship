exports.handler = async function(event, context) {
  console.log(JSON.stringify(event))
  return {
    statusCode: 200,
    body: JSON.stringify({ message: 'Hello' }),
    isBase64Encoded: false,
  }
}
