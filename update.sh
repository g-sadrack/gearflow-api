#!/bin/bash

# Parar e remover containers
docker compose down

# Construir projeto
mvn clean package

# Reconstruir imagem Docker
docker compose build --no-cache

# Iniciar containers
docker compose up -d

# Mostrar logs
docker compose logs -f app