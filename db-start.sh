DB_VERSION_TAG="alpine"
POSTGRES_USER="dummy_user"
POSTGRES_PASSWORD="Pa88w0rd"
POSTGRES_DB="product_db"

#Check network exists or create it
docker network ls | grep compose_default > /dev/null || docker network create compose_default

echo "Starting Postgres on 5432..."
CMD="docker run --rm -d --name postgres \
-p 5432:5432 \
-e POSTGRES_USER=${POSTGRES_USER} \
-e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
-e POSTGRES_DB=${POSTGRES_DB} \
-v /tmp/postgres/${POSTGRES_DB}/data:/var/lib/postgresql/data \
--network=compose_default \
postgres:${DB_VERSION_TAG}"

printf "Executing:\n%s" "${CMD}"
/bin/sh -c "${CMD}"

echo "Starting pgAdmin @ localhost:9999"
CMD="docker run --rm -d --name pgadmin \
-e PGADMIN_DEFAULT_EMAIL=${POSTGRES_USER}@none.com \
-e PGADMIN_DEFAULT_PASSWORD=${POSTGRES_PASSWORD} \
-v $(pwd)/database/servers.json:/pgadmin4/servers.json \
-p 9999:80 \
--network=compose_default \
dpage/pgadmin4:latest"

printf "Executing:\n%s" "${CMD}"
/bin/sh -c "${CMD}"
