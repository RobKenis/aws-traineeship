#!/usr/bin/env bash

mvn package

zip -r -j beanstalk.zip target/photo-album-0.0.1-SNAPSHOT.jar Procfile
