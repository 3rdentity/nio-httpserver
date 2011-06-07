#!/bin/bash
mvn package && java -cp target/*: me.shenfeng.http.NioHttpServer $@
