#!/bin/bash
docker run -d --name psql -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust postgres