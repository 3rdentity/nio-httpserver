A simple JAVA HTTP server
==================================

A simple java HTTP server. Understand GET and HEAD. It will do
directory list as well.

I first write it as an exercise for leaning JAVA NIO in late 2010,
`python -m SimpleHTTPSever [port]` is very handy, but a little slow for
me, so I use this as a replacement for daily use.

### how to run
1. install maven
2. `mvn package`
3. `./script/run.sh [port] [www-root]`

### TODO
1. More MIME mapping
2. Delivering large file
