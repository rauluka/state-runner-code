#!/usr/bin/env bash

RESOURCE_GROUP_NAME="cosmosdb-powered-factory"
ACCOUNT_NAME="androids-factory-account"
DB_NAME="androids-factory"
COLLECTION_NAME="androids"

echo "Creating resource group..."
az group create --location westeurope -n ${RESOURCE_GROUP_NAME} --verbose
echo "Resource group created."

echo "Creating cosmosdb account..."
az cosmosdb create -n ${ACCOUNT_NAME} -g ${RESOURCE_GROUP_NAME} \
  --locations regionName=westeurope failoverPriority=0 \
  --locations regionName=northeurope failoverPriority=1 \
  --default-consistency-level Session \
  --verbose

echo "Creating cosmosdb database..."
az cosmosdb database create --db-name ${DB_NAME} \
  -n ${ACCOUNT_NAME} \
  -g ${RESOURCE_GROUP_NAME} \
  --verbose

echo "Creating cosmosdb collection..."
az cosmosdb collection create -g ${RESOURCE_GROUP_NAME} \
  -n ${ACCOUNT_NAME} \
  --collection-name ${COLLECTION_NAME} \
  --db-name ${DB_NAME} \
  --partition-key-path "/identifier" \
  --verbose