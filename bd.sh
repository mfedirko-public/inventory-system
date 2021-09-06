#!/bin/bash
VERSION=${1:-"0.0.1"}
mvn clean install -DskipTests
docker build -t mifedirko/inventory-system:${VERSION} .
docker push mifedirko/inventory-system:${VERSION}
kubectl delete deployment inventory-backend
kubectl apply -k kube/kustomize/overlays/local/
watch -n1 kubectl get pod

