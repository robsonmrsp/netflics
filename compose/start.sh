#!/bin/sh

set -o errexit
set -o nounset

## this command will be executed in the folder /app because this is the workdir for this image
mvn spring-boot:run

