####generate certificate and keystore: keytool -genkey -alias lab.jks -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650 -dname "CN=certificate,OU=certificate,O=certificate,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1"
####add to application.properties: server.ssl.key-alias=soa

server.ssl.key-store=/path

server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=lab.jks


Add SSLConnectionSocketFactory to your httpClient so that it also knows about ssl (Only needed for second service in HttpClient if you send request from second service)

Check RequestService in SecondService for better understanding