
```shell
export ES_URL="https://localhost:9200"
export API_KEY="ZVB0LWJZMEJsdUZJekZYMXFfSk06U01US1FERjVTUHVZSkt1REx6cXBUUQ=="
```

# Test your connection
```shell
curl "${ES_URL}/search-notification-request" \
  -H "Authorization: ApiKey "${API_KEY}"" \
  -H "Content-Type: application/json"
```

# Ingest Data
```shell 
curl -X POST "${ES_URL}/_bulk?pretty&pipeline=ent-search-generic-ingestion" \
  -H "Authorization: ApiKey "${API_KEY}"" \
  -H "Content-Type: application/json" \
  -d'
{ "index" : { "_index" : "search-notification-request" } }
{"name": "Snow Crash", "author": "Neal Stephenson", "release_date": "1992-06-01", "page_count": 470, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
{ "index" : { "_index" : "search-notification-request" } }
{"name": "Revelation Space", "author": "Alastair Reynolds", "release_date": "2000-03-15", "page_count": 585, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
{ "index" : { "_index" : "search-notification-request" } }
{"name": "1984", "author": "George Orwell", "release_date": "1985-06-01", "page_count": 328, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
{ "index" : { "_index" : "search-notification-request" } }
{"name": "Fahrenheit 451", "author": "Ray Bradbury", "release_date": "1953-10-15", "page_count": 227, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
{ "index" : { "_index" : "search-notification-request" } }
{"name": "Brave New World", "author": "Aldous Huxley", "release_date": "1932-06-01", "page_count": 268, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
{ "index" : { "_index" : "search-notification-request" } }
{"name": "The Handmaid'"'"'s Tale", "author": "Margaret Atwood", "release_date": "1985-06-01", "page_count": 311, "_extract_binary_content": true, "_reduce_whitespace": true, "_run_ml_inference": true}
'
```
## Ingestion pipeline
```shell
curl -X POST 'https://localhost:9200/search-notification-request/_doc?pipeline=ent-search-generic-ingestion' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: ApiKey <Replace_with_created_API_key>' \
  -d '{
  "body": "body",
  "title": "Title",
  "_extract_binary_content": true,
  "_reduce_whitespace": true,
  "_run_ml_inference": true
}'
```

# Build your first search query
```shell
curl -X POST "${ES_URL}/search-notification-request/_search?pretty" \
  -H "Authorization: ApiKey "${API_KEY}"" \
  -H "Content-Type: application/json" \
  -d'
{
  "query": {
    "query_string": {
      "query": "snow"
    }
  }
}'
```
## API
```json
{
"id": "G3ineI0BhfdXPYlv1fXM",
"name": "notificationService",
"api_key": "FIUTEqGBTSm7RMjH75hxRw",
"encoded": "ZU1OQWZZMEJDR19NOFhoY3JNZFI6aDJJcmtnSnNUVktuS29VT2RzVUpfQQ==",
"beats_logstash_format": "G3ineI0BhfdXPYlv1fXM:FIUTEqGBTSm7RMjH75hxRw"
}
```
