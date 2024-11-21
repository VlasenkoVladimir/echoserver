# .env for all variables
# NO extra space like foo = bar
# _______________________________
# Port for server
ECHOSERVER_PORT=8081

# Input buffer (integer) 
INPUT_BUFFER_SIZE=1024

# ping/pong timeout in milliseconds
IDLE_TIMEOUT=30000

# Name of container for docker
APP_CONTAINER_NAME=echoserverapp

# internal/external ports for container
APP_CONTAINER_PORTS=8081:8081

# To build with wrapper from project root directory
./gradlew build

# Make container from project root directory
docker build --no-cache --tag echoserverapp .

# For start container
docker run --env-file=.env -i echoserverapp