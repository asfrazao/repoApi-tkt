docker rm -f api-ticket

docker build . -t api-ticket

docker run -d -it --name api-ticket -p 8081:8081 -v /opt/digivox/citrus/attachments/socialMedia/:/app/socialMedia api-ticket