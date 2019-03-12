#!/bin/bash
nohup /usr/share/logstash/bin/logstash  -f ./jdbc.conf > sync.log 2>&1 &