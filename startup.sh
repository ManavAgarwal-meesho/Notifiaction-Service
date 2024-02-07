#!/bin/zsh

# Start ZooKeeper server in a new terminal
osascript -e 'tell app "Terminal" to do script "/opt/homebrew/bin/zookeeper-server-start /opt/homebrew/etc/zookeeper/zoo.cfg"'

# Add a 100ms delay
sleep 0.1

# Start Redis Server
osascript -e 'tell app "Terminal" to do script "brew services start redis"'

# Add a 100ms delay
sleep 0.1

# Start Redis Server
osascript -e 'tell app "Terminal" to do script "brew services start mysql"'

# Add a 500ms delay
sleep 0.5

# Start Redis Server
osascript -e 'tell app "Terminal" to do script "redis-server"'

# Add a 100ms delay
sleep 0.1

# Start Elastic Search Server
osascript -e 'tell app "Terminal" to do script "/Users/manavagarwal/elasticsearch-8.12.0/bin/elasticsearch"'

# Add a 500ms delay
sleep 0.5

# Start Kibana Server
osascript -e 'tell app "Terminal" to do script "/Users/manavagarwal/kibana-8.12.0/bin/kibana"'

# Add a 500ms delay
sleep 0.5

# Start Kafka server in a new terminal
osascript -e 'tell app "Terminal" to do script "/opt/homebrew/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties"'
