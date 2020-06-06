#!/usr/bin/env bash

RESOURCE_GROUP_NAME="azure-sql-powered-factory"

az group create --location westus -n ${RESOURCE_GROUP_NAME}

az group deployment create --resource-group ${RESOURCE_GROUP_NAME} --template-file azure-sql.json --parameters azure-sql-params.json --verbose  --debug
