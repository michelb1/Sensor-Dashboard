#bin/sh
ps aux | grep -i sensordataserver.jar | grep -v grep | awk '{print $2}' | xargs kill
